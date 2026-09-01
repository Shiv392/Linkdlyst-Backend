package com.example.Linkdlyst.Features.Auth.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Linkdlyst.Features.Auth.Dto.LoginRequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("/login")
    public String login(
       @Valid @RequestBody LoginRequestBody loginRequestBody) {
        return "Login Controller";
    }

    @PostMapping("/signup")
    public String signup(
        @Valid @RequestBody SignupRequestBody signupRequestBody
    ) {
        return "Signup Controller";
    }
}
