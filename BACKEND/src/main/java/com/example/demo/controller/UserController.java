/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
     ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        return ApiResponse.<UserResponse>builder()
                .data(userService.createUser(userCreationRequest))
                .build();
    }
    @GetMapping("/{id}")
    ApiResponse<UserResponse> getUser(@PathVariable Long id){
        return ApiResponse.<UserResponse> builder().
                data(userService.getUser(id))
                .build();
    }
    @PutMapping("/{id}")
    ApiResponse<UserResponse> updateUser(@PathVariable Long id,@RequestBody @Valid UserUpdateRequest userUpdateRequest){
        return ApiResponse.<UserResponse>builder().
                data(userService.updateUser(id, userUpdateRequest)).build();
    }
    @DeleteMapping("/{id}")
    ApiResponse<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ApiResponse.<String>builder().data("User has been deleted").build();
    }
    @PutMapping("toggle/{id}")
    ApiResponse<String> toggleUser(@PathVariable Long id){
        userService.toggleActiveUser(id);
        return ApiResponse.<String>builder().data("Toggle user's status successfully").build();
    }
}
