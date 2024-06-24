package com.atm.accountServices.constants;

import lombok.Getter;

@Getter
public enum ErrorResponse {
    // General Errors
    ACCOUNT_NOT_FOUND(1000, "Account not found."),
    ACCOUNT_ALREADY_EXISTS(1001, "Account already present."),
    // Authentication errors
    AUTHENTICATION_FAILED(2000, "Authentication failed."),

    // Validation errors
    VALIDATION_FAILED(3000, "Validation failed."),
    MISSING_REQUIRED_FIELD(3001, "Missing required field."),
    INVALID_FIELD_VALUE(3002, "Invalid field value.");

    private final int code;
    private final String message;

    ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
