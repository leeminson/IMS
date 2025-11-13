/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

/**
 *
 * @author PC
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Uncategorized error",HttpStatus.INTERNAL_SERVER_ERROR),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"Resoure not found",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED.value(),"Unauthentication",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(HttpStatus.FORBIDDEN.value(),"You do not have permission",HttpStatus.FORBIDDEN),
    INVALID_KEY(HttpStatus.BAD_REQUEST.value(),"Bad request",HttpStatus.BAD_REQUEST),
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT.value(), "Resource already exists", HttpStatus.CONFLICT);
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    
}
