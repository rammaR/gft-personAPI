package com.rammar.me.beerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BeerDuplicateException extends Exception {

    public BeerDuplicateException(String message) {
        super(message);
    }

}
