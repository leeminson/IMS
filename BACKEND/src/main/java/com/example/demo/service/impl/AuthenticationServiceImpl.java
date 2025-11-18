/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service.impl;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.JWTUlti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private final PasswordEncoder passwordEncoder ;
    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
    private final JWTUlti jwtUtil ;
    @Autowired
    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository
//            , AuthenticationManager authenticationManager
            , JWTUlti jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user=userRepository.findByUsername(request.getUsername()).orElseThrow(()->new AppException(ErrorCode.RESOURCE_NOT_FOUND,"User with "+request.getUsername()+" not found"));
        boolean authenticated = passwordEncoder.matches(request.getPassword(),
                user.getPassword());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED,"Wrong password");
        String jwt=jwtUtil.generateJwtToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .authenticated(true).build();
    }

    
    
}
