package com.udacity.bootstrap.Web;

import com.udacity.bootstrap.Services.DogNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDogNotFoundException(DogNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        System.out.println("Hey, the Err = " + ex.getMessage());
        return "error-page"; // returns a view with the error message
    }
}