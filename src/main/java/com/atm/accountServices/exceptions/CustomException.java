package com.atm.accountServices.exceptions;

import com.atm.accountServices.constants.ErrorResponse;
import lombok.Getter;

@Getter
public class AccountAlreadyExistsException extends Exception{

    private final ErrorResponse errorResponse;

    public AccountAlreadyExistsException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage()); // Set the error message using ErrorCode's message
        this.errorResponse = errorResponse;
    }
}