package com.rammar.me.beerapi.exceptions;

public class BeerOverflowStockException extends Exception {

    public BeerOverflowStockException(Long id, Integer max){
        super(String.format("Beer %s cannot have more than %d in your stock.", id, max));
    }

}
