package com.rammar.me.beerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFoundException extends Exception {
    public BeerNotFoundException(String name) {
        super(String.format("Beer with name %s not found in the system.", name));
    }
}
