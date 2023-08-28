package com.example.orderapis.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()),false,new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getLocalizedMessage(),Arrays.asList(ex.getMessage()),false,new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getLocalizedMessage(),Arrays.asList(ex.getMessage()),false,new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handleRuntimeException(ResponseStatusException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getLocalizedMessage(),Arrays.asList(ex.getMessage()),false,new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails>handleRecordNotFoundException(RecordNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getLocalizedMessage(),Arrays.asList(ex.getMessage()),false,new Date());
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);

    }
}
