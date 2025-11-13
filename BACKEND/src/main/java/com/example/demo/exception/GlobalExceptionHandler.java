/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.exception;

import com.example.demo.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author PC
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handlingException(Exception exception){
        log.error("Exception : "+exception.getMessage());
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handlingAppException(AppException appException){
        log.error("App exception : "+appException.getMessage());
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setCode(appException.getErrorCode().getCode());
        apiResponse.setStatus(appException.getErrorCode().getMessage());
        apiResponse.setMessage(appException.getMessage());
        return ResponseEntity.status(appException.getErrorCode().getStatusCode()).body(apiResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){
        log.error("Validation exception : "+exception.getMessage());
        String enumkey=exception.getFieldError().getDefaultMessage();
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setCode(ErrorCode.INVALID_KEY.getCode());
        apiResponse.setStatus(ErrorCode.INVALID_KEY.getMessage());
        apiResponse.setMessage(enumkey);
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
