package com.ejercicios.DB1jpa.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFExceptions extends RuntimeException {

    public NotFExceptions(String message){
        super(message);
    }
}
