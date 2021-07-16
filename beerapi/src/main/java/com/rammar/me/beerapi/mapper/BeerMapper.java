package com.rammar.me.beerapi.mapper;

import com.rammar.me.beerapi.dto.BeerDTO;
import com.rammar.me.beerapi.entity.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {

    BeerMapper beerMapper = Mappers.getMapper(BeerMapper.class);

    BeerDTO toDTO(Beer beer);

    Beer toModel(BeerDTO beerDTO);

}
