/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PC
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserCreationRequest {
    @NotBlank(message = "Username must not be blank")
    private String username;    
    private String password;
    @NotBlank(message = "Name must not be blank")
    private String fullname;
    private LocalDate dob;
}
