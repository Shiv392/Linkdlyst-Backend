package com.example.Linkdlyst.Features.Auth.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Linkdlyst.Features.Auth.Dto.LoginRequestBody;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import com.example.Linkdlyst.Features.Auth.Services.SignupService;
import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;

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
    public ResponseEntity<GlobalApiResponse> signup(
        @Valid @RequestBody SignupRequestBody signupRequestBody
    ) {
        boolean isSignedUp = signupService.signup(signupRequestBody);
        if (isSignedUp) {
            return ResponseEntity.ok(new GlobalApiResponse<>(true, "User signed up successfully", null));
        } else {
            return ResponseEntity.status(500).body(new GlobalApiResponse<>(false, "Failed to sign up user", null));
        }
    }
}
