package com.rammar.me.jornadaAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JornadaNotFoundExcetion extends Exception {
    public JornadaNotFoundExcetion(Long id) {
        super("Jornada de Trabalho de ID igual a " + id + " não foi encontrada ou não existe.");
    }
}
