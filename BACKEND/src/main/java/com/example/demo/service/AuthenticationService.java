/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;

/**
 *
 * @author PC
 */
public interface AuthenticationService {
    boolean authenticate(AuthenticationRequest request);
}
