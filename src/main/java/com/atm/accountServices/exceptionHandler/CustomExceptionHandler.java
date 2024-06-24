package com.atm.accountServices.exceptionHandler;

import com.atm.accountServices.exceptions.AccountAlreadyExistsException;
import com.atm.accountServices.exceptions.AccountNotFoundException;
import com.atm.accountServices.models.ErrorStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorStructure> handleItemNotFoundException(AccountNotFoundException e) {
        return new ResponseEntity<>(ErrorStructure.builder().errorMessage(e.getMessage()).errorCode(e.getErrorResponse().getCode()).timestamp(LocalDateTime.now()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<ErrorStructure> handleItemAlreadyExistsException(AccountAlreadyExistsException e) {
        return new ResponseEntity<>(ErrorStructure.builder().errorMessage(e.getMessage()).errorCode(e.getErrorResponse().getCode()).timestamp(LocalDateTime.now()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorStructure> handelMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ErrorStructure.builder().errorMessage(e.getMessage()).errorCode(400).timestamp(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorStructure> handelGenericException(Exception e) {
        return new ResponseEntity<>(ErrorStructure.builder().errorMessage(e.getMessage()).errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

