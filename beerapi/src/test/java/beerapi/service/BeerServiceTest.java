package beerapi.service;


import beerapi.utils.BeerUtils;
import com.rammar.me.beerapi.dto.BeerDTO;
import com.rammar.me.beerapi.entity.Beer;
import com.rammar.me.beerapi.exceptions.BeerDuplicateException;
import com.rammar.me.beerapi.exceptions.BeerNotFoundException;
import com.rammar.me.beerapi.exceptions.BeerOverflowStockException;
import com.rammar.me.beerapi.mapper.BeerMapper;
import com.rammar.me.beerapi.repository.BeerRepository;
import com.rammar.me.beerapi.service.BeerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static beerapi.utils.BeerUtils.createFakeBeer;
import static beerapi.utils.BeerUtils.createFakeBeerDTO;
import static com.rammar.me.beerapi.utils.MyStrings.STR_CREATED_BEER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.beerMapper;

    @InjectMocks
    private BeerService beerService;

    @Test
    public void testGivenAValidPersonDTOShouldCreated() throws BeerDuplicateException {
        BeerDTO fakeBeerDTO = BeerUtils.createFakeBeerDTO();
        Beer fakeBeer = BeerUtils.createFakeBeer();
        when(beerRepository.findByName(fakeBeerDTO.getName())).thenReturn(Optional.empty());
        when(beerMapper.toModel(fakeBeerDTO)).thenReturn(fakeBeer);
        when(beerMapper.toDTO(fakeBeer)).thenReturn(fakeBeerDTO);
        when(beerRepository.save(fakeBeer)).thenReturn(fakeBeer);
        //
        BeerDTO savedBeer = beerService.create(fakeBeerDTO);
        //
        assertEquals(fakeBeerDTO, savedBeer);
        assertThat(fakeBeerDTO.getName(), is(equalTo(savedBeer.getName())));
        assertThat(fakeBeerDTO.getBrand(), is(equalTo(savedBeer.getBrand())));
        assertThat(fakeBeerDTO.getQuantity(), is(equalTo(savedBeer.getQuantity())));
    }

    @Test
    public void testCannotAllowedMoreThanABeerWithSameNameOrBrand() throws BeerDuplicateException {
        BeerDTO fakeBeerDTO = BeerUtils.createFakeBeerDTO();
        Optional<Beer> optionalBeer = Optional.of(BeerUtils.createFakeBeer());
        when(beerRepository.findByName(fakeBeerDTO.getName())).thenReturn(optionalBeer);
        //
        Assert.assertThrows(BeerDuplicateException.class, () -> {
            beerService.create(fakeBeerDTO);
        });
        fakeBeerDTO.setName("Different Name of Beer");
        Assert.assertThrows(BeerDuplicateException.class, () -> {
            beerService.create(fakeBeerDTO);
        });
    }

    @Test
    public void returnBeerWhoMatchWithTheGivenName() throws BeerNotFoundException {
        BeerDTO fakeBeerDTO = BeerUtils.createFakeBeerDTO();
        Beer beer = BeerUtils.createFakeBeer();
        String name = fakeBeerDTO.getName();
        when(beerRepository.findByName(name)).thenReturn(Optional.ofNullable(beer));
        //
        BeerDTO returnedBeer = beerService.findByName(name);
        //
        assertEquals(fakeBeerDTO, returnedBeer);
        assertThat(fakeBeerDTO.getName(), is(equalTo(returnedBeer.getName())));
        assertThat(fakeBeerDTO.getBrand(), is(equalTo(returnedBeer.getBrand())));
        assertThat(fakeBeerDTO.getQuantity(), is(equalTo(returnedBeer.getQuantity())));
    }

    @Test
    public void returnExceptionIfNotExistsBeerWithTheGivenName() throws BeerNotFoundException {
        String invalidName = "Invalid Beer";
        when(beerRepository.findByName(invalidName)).thenReturn(Optional.empty());

        Assert.assertThrows(BeerNotFoundException.class, () -> {
            beerService.findByName(invalidName);
        });
    }

    @Test
    void testReturnAListOfBeers() {
        // given
        BeerDTO expectedFoundBeerDTO = createFakeBeerDTO();
        Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);

        //when
        when(beerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBeer));

        //then
        List<BeerDTO> foundListBeersDTO = beerService.listAll();

        assertThat(foundListBeersDTO, is(not(empty())));
        assertThat(foundListBeersDTO.get(0), is(equalTo(expectedFoundBeerDTO)));
    }

    @Test
    void testToReturnAEmptyListOfBeers() {
        when(beerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        //
        List<BeerDTO> foundedListOfBeers = beerService.listAll();
        //
        assertThat(foundedListOfBeers, is(empty()));
    }

    @Test
    void testDeleteAExistingBeerShouldReturnNoContent() throws BeerNotFoundException {
        BeerDTO fakeBeerDTO = createFakeBeerDTO();
        when(beerRepository.findById(fakeBeerDTO.getId())).thenReturn(Optional.ofNullable(createFakeBeer()));
        doNothing().when(beerRepository).deleteById(fakeBeerDTO.getId());
        //
        beerService.delete(fakeBeerDTO.getId());
        //
        verify(beerRepository, times(1)).findById(fakeBeerDTO.getId());
        verify(beerRepository, times(1)).deleteById(fakeBeerDTO.getId());
    }

    @Test
    void testDeleteWithAInvalidIDShouldReturnNotFoundException() throws BeerNotFoundException {
        Long invalidID = 985l;

        when(beerRepository.findById(invalidID)).thenReturn(Optional.empty());
        //doNothing().when(beerRepository).deleteById(invalidID);

        Assert.assertThrows(BeerNotFoundException.class, () -> {
            beerService.delete(invalidID);
        });
    }

    @Test
    void testIncrementOfASpecificBeerShouldReturnSucess() throws BeerNotFoundException, BeerOverflowStockException {
        BeerDTO beerDTO = createFakeBeerDTO();
        Beer beer = createFakeBeer();
        Integer newQuantity = 5;
        when(beerRepository.findById(beer.getId())).thenReturn(Optional.ofNullable(beer));
        //
        BeerDTO incrementedBeerDTO = beerService.increment(beerDTO.getId(), newQuantity);
        //
        assertThat(incrementedBeerDTO.getQuantity(), is(beerDTO.getQuantity() + newQuantity));
        assertThat(incrementedBeerDTO.getQuantity(), lessThan(beerDTO.getMax()));
    }

    @Test
    void testIfIncrementIsGreaterThanMaxThenThrowAException() throws BeerNotFoundException {
        BeerDTO beerDTO = createFakeBeerDTO();
        Beer beer = createFakeBeer();
        Integer newQuantity = 9999;
        when(beerRepository.findById(beer.getId())).thenReturn(Optional.ofNullable(beer));
        //
        assertThrows(BeerOverflowStockException.class, () -> {
            BeerDTO incrementedBeerDTO = beerService.increment(beerDTO.getId(), newQuantity);
        });
    }

}
