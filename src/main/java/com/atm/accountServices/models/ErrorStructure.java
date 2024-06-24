package com.atm.accountServices.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorStructure {
    private String errorMessage;
    private int errorCode;
    private LocalDateTime timestamp;
}
