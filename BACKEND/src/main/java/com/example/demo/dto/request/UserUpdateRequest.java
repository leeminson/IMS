/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto.request;

import com.example.demo.enumeration.Role;
import jakarta.validation.constraints.Pattern;
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
public class UserUpdateRequest {
    private String fullname;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,12}$",message = "Password must contain at least 1 uppercase letter, 1 number, and be 8-12 characters long")
    private String password;
    private Role role;
    private LocalDate dob;
}
