package com.example.Linkdlyst.Features.Auth.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Linkdlyst.Features.Auth.Dto.LoginRequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import com.example.Linkdlyst.Features.Auth.Services.SignupService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final SignupService signupService;

    public AuthController(SignupService _signupService) {
        signupService = _signupService;
    }

    @PostMapping("/login")
    public String login(
       @Valid @RequestBody LoginRequestBody loginRequestBody) {
        return "Login Controller";
    }

    @PostMapping("/signup")
    public String signup(
        @Valid @RequestBody SignupRequestBody signupRequestBody
    ) {
        return signupService.signup(signupRequestBody);
    }
}
