/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.mapper;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author PC
 */
@Component
public class UserMapper {
    private final ModelMapper mapper;
    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public UserResponse toResponse(User u){
        return mapper.map(u,UserResponse.class);
    }
    public User createRequestToEntity(UserCreationRequest request){
        return mapper.map(request,User.class);
    }
    public void updateRequestToEntity(UserUpdateRequest request,User user){
        mapper.typeMap(UserUpdateRequest.class, User.class).addMappings((mapping) -> {
            mapping.skip(User::setUsername);
            mapping.skip(User::setIsDeleted);
            mapping.skip(User::setId);            
        });
        mapper.map(request, user);
    }
}
