package com.example.todoapi.common;

import com.example.todoapi.common.dto.ErrorResponse;
import com.example.todoapi.common.exception.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler { //extends ResponseEntityExceptionHandler
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    // 우리가 직접 BadRequestException 에러 클래스로 에러를 생성해서 발생시켰을 때 처리
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    // DTO 에서 유효성 검사가 실패했을 때 대신 응답하는 핸들러
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return ResponseEntity.badRequest().body(errorResponse);
    }



/*
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        ErrorCode.ErrorResponse errorResponse = new ErrorCode.ErrorResponse();
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    //우리가 직접 BadRequestException 에러 클래스로 에러를 생성해서 발생시켰을 때 처리
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorCode.ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorCode.ErrorResponse errorResponse = new ErrorCode.ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }


    // DTO 에서 유효성 검사가 실패했을 때 대신 응답하는 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @Override
    public ResponseEntity<ErrorCode.ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorCode errorCode = ErrorCode.VALIDATION_FAILED;
        return ResponseEntity.status(errorCode.getHttpStatus()).body(new ErrorCode.ErrorResponse(errorCode));
    }
    */
}
