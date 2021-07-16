package beerapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rammar.me.beerapi.dto.BeerDTO;
import com.rammar.me.beerapi.entity.Beer;
import com.rammar.me.beerapi.enums.BeerType;
import com.rammar.me.beerapi.mapper.BeerMapper;

public class BeerUtils {

    public static BeerDTO createFakeBeerDTO() {
        return BeerDTO.builder()
                .id(999l)
                .brand("PILSEN")
                .max(500)
                .name("PILSON")
                .quantity(50)
                .type(BeerType.WITBIER)
                .build();
    }

    public static Beer createFakeBeer() {
        return BeerMapper.beerMapper.toModel(createFakeBeerDTO());
    }

    public static String asJSONString(BeerDTO beerDTO) {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "";
        try {
            json = objectMapper.writeValueAsString(beerDTO);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
