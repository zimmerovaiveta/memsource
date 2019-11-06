package com.example.memsource.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public abstract class RestController {

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<NotFoundError.NotFoundErrorData> handleNotFoundException(NotFoundError ex) {
        return new ResponseEntity<>(ex.getData(), HttpStatus.NOT_FOUND);
    }
}
