package com.example.PAF.dtos;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String headline;
    private String password;
} 