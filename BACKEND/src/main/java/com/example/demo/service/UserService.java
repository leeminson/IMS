/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import java.util.List;

/**
 *
 * @author PC
 */
public interface UserService {
    UserResponse createUser(UserCreationRequest ucr);
    void deleteUser(Long id);
    UserResponse updateUser(Long id,UserUpdateRequest uur);
    UserResponse getUser(Long id);
    UserResponse getMyInfo();
    List<UserResponse> getAllUser();
    UserResponse getUserByUsername(String username);
    void toggleActiveUser(Long id);
}
