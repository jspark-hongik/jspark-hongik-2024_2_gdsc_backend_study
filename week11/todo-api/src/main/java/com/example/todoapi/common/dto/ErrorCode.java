package com.example.todoapi.common.dto;
/*
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public enum ErrorCode {
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "Validation failed for argument");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private final LocalDateTime timestamp = LocalDateTime.now();
        private final int statusCode;
        private final String error;
        private final String message;

        public ErrorResponse(ErrorCode errorCode) {
            this.statusCode = errorCode.getHttpStatus().value();
            this.error = errorCode.getHttpStatus().name();
            this.message = errorCode.getMessage();
        }
    }
}
*/

