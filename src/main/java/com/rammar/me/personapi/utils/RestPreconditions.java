package com.rammar.me.personapi.utils;

import com.rammar.me.personapi.exceptions.MyResourceNotFoundException;

public class RestPreconditions {

    public static <T> T checkFound(T resource) throws MyResourceNotFoundException {
        if (resource == null) {
            throw new MyResourceNotFoundException("");
        }
        return resource;
    }
}
