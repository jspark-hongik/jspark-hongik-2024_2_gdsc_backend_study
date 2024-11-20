package com.example.todoapi.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

}
