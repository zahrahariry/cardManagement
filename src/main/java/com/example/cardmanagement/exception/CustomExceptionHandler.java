package com.example.cardmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CardNotFoundException.class)
    public ResponseEntity<Object> throwCardNotFoundException (CardNotFoundException cardNotFoundException){
        return new ResponseEntity<>("CardNotFoundException", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CardCurrentlyIsExistsException.class)
    public ResponseEntity<Object> throwCardCurrentlyIsExistsException (CardCurrentlyIsExistsException cardCurrentlyIsExistsException){
        return new ResponseEntity<>("CardCurrentlyIsExistsException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<Object> throwPersonNotFoundException (PersonNotFoundException personNotFoundException){
        return new ResponseEntity<>("PersonNotFoundException", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PersonCurrentlyIsExistsException.class)
    public ResponseEntity<Object> throwPersonCurrentlyIsExistsException (PersonCurrentlyIsExistsException personCurrentlyIsExistsException){
        return new ResponseEntity<>("PersonCurrentlyIsExistsException", HttpStatus.BAD_REQUEST);
    }
}
