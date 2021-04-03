package com.udinei.innovation.one.heroisapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeroiNotFoundException extends Exception {

    public HeroiNotFoundException(String heroiName) {
        super(String.format("Heroi com name %s not found in the system.", heroiName));

    }

    public HeroiNotFoundException(Long id){
        super(String.format("Heroi with id %s not found in the system.", id));
       }
}
