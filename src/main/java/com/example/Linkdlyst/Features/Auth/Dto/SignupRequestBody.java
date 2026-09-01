package com.example.Linkdlyst.Features.Auth.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupRequestBody {

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Size(max = 50, message = "Email should not be greater than 50 characters")
    @Size(min = 5, message = "Email should not be less than 5 characters")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotNull(message = "Password is required")
    private String password;

    public SignupRequestBody(){}

    public SignupRequestBody(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
