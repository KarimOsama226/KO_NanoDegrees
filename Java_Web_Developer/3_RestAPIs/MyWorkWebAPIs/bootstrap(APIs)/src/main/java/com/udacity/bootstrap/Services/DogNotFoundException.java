package com.udacity.bootstrap.Services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dog not found")
public class DogNotFoundException extends RuntimeException {

    public DogNotFoundException() {
        super("Dog not found");
    }

    public DogNotFoundException(String message) {
        super("Dog not found");
    }
}
