package com.example.Linkdlyst.Features.Auth.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LoginRequestBody {
    
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;


    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;

    public LoginRequestBody() {
    }

    public LoginRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
