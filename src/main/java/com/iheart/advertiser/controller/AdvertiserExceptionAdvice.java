package com.iheart.advertiser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdvertiserExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(AdvertiserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleAdvertiserNotFound(AdvertiserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AdvertiserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleAdvertiserAlreadyExists(AdvertiserAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AdvertiserFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleAdvertiserBadlyFormatted(AdvertiserFormatException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CreditFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleNumberFormatException(CreditFormatException ex) {
        return ex.getMessage();
    }
}
