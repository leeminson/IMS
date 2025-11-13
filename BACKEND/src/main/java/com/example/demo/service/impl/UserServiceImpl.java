/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service.impl;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.enumeration.Role;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author PC
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(UserCreationRequest ucr) {
        User user = userMapper.createRequestToEntity(ucr);
        user.setPassword(passwordEncoder.encode(ucr.getPassword()));
        user.setRole(Role.USER);
        user.setIsDeleted(Boolean.FALSE);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.DUPLICATE_RESOURCE, "User with " + user.getUsername() + " is already existed");
        }
        return userMapper.toResponse(user);

    }

    @Override
    public void deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "User not found"));
        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            user.setIsDeleted(Boolean.TRUE);
            userRepository.save(user);
        }
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest uur) {
        User user=userRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.RESOURCE_NOT_FOUND, "User not found"));
        System.out.println(user);
        userMapper.updateRequestToEntity(uur,user);
        user.setPassword(passwordEncoder.encode(uur.getPassword()));
        System.out.println(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.RESOURCE_NOT_FOUND, "User not found"));
        return userMapper.toResponse(user);

    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> userMapper.toResponse(user))
                .collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User user;
        try {
             user=userRepository.findByUsername(username);
        } catch (AppException e) {
            throw new AppException(ErrorCode.RESOURCE_NOT_FOUND, "User with "+ username +" not found");
        }
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getMyInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void toggleActiveUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "User not found"));
        user.setIsDeleted(!user.getIsDeleted());
    }

}
