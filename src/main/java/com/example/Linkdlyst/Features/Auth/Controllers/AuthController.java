package com.example.Linkdlyst.Features.Auth.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Linkdlyst.Features.Auth.Dto.LoginRequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.LoginResponseBody;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import com.example.Linkdlyst.Features.Auth.Services.LoginService;
import com.example.Linkdlyst.Features.Auth.Services.SignupService;
import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Value("${app.temp-name}")
    private String tempName;

    public AuthController(SignupService _signupService, LoginService _loginService) {
        signupService = _signupService;
        loginService = _loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalApiResponse> login(
       @Valid @RequestBody LoginRequestBody loginRequestBody) {
        LoginResponseBody loginResponse = loginService.login(loginRequestBody);
        logger.info("Temp Name: "+tempName);
        
        return ResponseEntity.ok(new GlobalApiResponse<>(true, "User logged in successfully", loginResponse));
    }

    @PostMapping("/signup")
    public ResponseEntity<GlobalApiResponse> signup(
        @Valid @RequestBody SignupRequestBody signupRequestBody
    ) {
        boolean isSignedUp = signupService.signup(signupRequestBody);
        if (isSignedUp) {
            return ResponseEntity.ok(new GlobalApiResponse<>(true, "Otp has been sent to your email", null));
        } else {
            return ResponseEntity.status(500).body(new GlobalApiResponse<>(false, "Failed to sign up user", null));
        }
    }
}
