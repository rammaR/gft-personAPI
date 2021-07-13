package beerapi.controller;

import com.rammar.me.beerapi.controller.BeerController;
import com.rammar.me.beerapi.dto.BeerDTO;
import com.rammar.me.beerapi.entity.Beer;
import com.rammar.me.beerapi.exceptions.BeerDuplicateException;
import com.rammar.me.beerapi.exceptions.BeerNotFoundException;
import com.rammar.me.beerapi.repository.BeerRepository;
import com.rammar.me.beerapi.service.BeerService;
import org.apiguardian.api.API;
import org.h2.mvstore.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static beerapi.utils.BeerUtils.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
public class BeerControllerTest {

    private static final String API_URL = "/api/v1/beers";
    @Mock
    private BeerService beerService;

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerController beerController;

    private MockMvc mockMVC;

    @BeforeEach
    void setUp() {
        mockMVC = MockMvcBuilders.standaloneSetup(beerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testPostANewBeer() throws Exception, BeerDuplicateException {
        BeerDTO fakeBeerDTO = createFakeBeerDTO();
        when(beerService.create(fakeBeerDTO)).thenReturn(fakeBeerDTO);
        //
        mockMVC.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON).content(asJSONString(fakeBeerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(fakeBeerDTO.getName())))
                .andExpect(jsonPath("$.brand", is(fakeBeerDTO.getBrand())))
                .andExpect(jsonPath("$.type", is(fakeBeerDTO.getType().toString())));
    }

    @Test
    void testBadRequest() throws Exception, BeerDuplicateException {
        BeerDTO fakeBeerDTO = createFakeBeerDTO();
        fakeBeerDTO.setName("");
        when(beerService.create(fakeBeerDTO)).thenReturn(fakeBeerDTO);
        //
        mockMVC.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON).content(asJSONString(fakeBeerDTO)))
                .andExpect(status().isBadRequest());
        fakeBeerDTO.setName("CERVEJA TESTE");
        fakeBeerDTO.setBrand("");
        mockMVC.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON).content(asJSONString(fakeBeerDTO)))
                .andExpect(status().isBadRequest());
        fakeBeerDTO.setBrand("BRAND TESTE");
        fakeBeerDTO.setName(new String("T").repeat(256));
        mockMVC.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON).content(asJSONString(fakeBeerDTO)))
                .andExpect(status().isBadRequest());
        fakeBeerDTO.setName(new String("T").repeat(255));
        fakeBeerDTO.setMax(501);
        mockMVC.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON).content(asJSONString(fakeBeerDTO)))
                .andExpect(status().isBadRequest());
        fakeBeerDTO.setMax(500);
        mockMVC.perform(post(API_URL).contentType(MediaType.APPLICATION_JSON).content(asJSONString(fakeBeerDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testWhenGETBeerWithValidFieldsThenSuccess() throws Exception, BeerNotFoundException {
        BeerDTO fakeBeer = createFakeBeerDTO();
        when(beerService.findByName(fakeBeer.getName())).thenReturn(fakeBeer);

        mockMVC.perform(get(API_URL + "/" + fakeBeer.getName()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(fakeBeer.getName())))
                .andExpect(jsonPath("$.brand", is(fakeBeer.getBrand())))
                .andExpect(jsonPath("$.type", is(fakeBeer.getType().toString())));
    }

    @Test
    void testWhenGetBeerWithInvalidFieldsThenFail() throws BeerNotFoundException, Exception {
        String invalidName = "INVALID";
        when(beerService.findByName(invalidName)).thenThrow(BeerNotFoundException.class);
        mockMVC.perform(MockMvcRequestBuilders.get(API_URL + "/" + invalidName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testWhenGetWithoutParamsThenReturnAListOfBeers() throws Exception {
        BeerDTO beerDto = createFakeBeerDTO();
        when(beerService.listAll()).thenReturn(Collections.singletonList(beerDto));
        //
        mockMVC.perform(get(API_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(beerDto.getName())))
                .andExpect(jsonPath("$[0].brand", is(beerDto.getBrand())));
    }

    @Test
    void testDeleteANonExistsBeerShouldReturnNotFoundException() throws Exception {
        Long invalidID = 879l;
        doThrow(BeerNotFoundException.class).when(beerService).delete(invalidID);
        //
        mockMVC.perform(delete(API_URL + "/" + invalidID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCallDeleteInEmptyListShouldReturnNotFoundException() throws Exception {
        BeerDTO beerDTO = createFakeBeerDTO();
        doNothing().when(beerService).delete(beerDTO.getId());
        //
        mockMVC.perform(delete(API_URL + "/" + beerDTO.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
