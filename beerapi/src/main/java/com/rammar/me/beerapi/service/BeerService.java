package com.rammar.me.beerapi.service;

import com.rammar.me.beerapi.mapper.BeerMapper;
import com.rammar.me.beerapi.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    //private final BeerMapper beerMapper = BeerMapper.INSTANCE;

}
