///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.example.demo.service.impl;
//
//import com.example.demo.entity.User;
//import com.example.demo.exception.AppException;
//import com.example.demo.exception.ErrorCode;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.util.CustomUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author PC
// */
//@Service
//public class CustomUserDetailsServiceImpl implements UserDetailsService{
//    private final UserRepository userRepository ;
//    @Autowired
//    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "User with username : "+ username +" not found"));
//        return new CustomUserDetails(user);  
//    }
//
//    
//    
//}
