/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.service.impl.AuthenticationServiceImpl;
import com.example.demo.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthenticationServiceImpl authenticationServiceImpl;
    @Autowired

    public AuthController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }
    @PostMapping
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationServiceImpl.authenticate(request))
                .build();
    }
}
