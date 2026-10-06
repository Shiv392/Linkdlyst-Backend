package com.example.Linkdlyst.Features.Auth.Controllers;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Linkdlyst.Features.Auth.Dto.LoginRequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.LoginResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import com.example.Linkdlyst.Features.Auth.Services.JwtService;
import com.example.Linkdlyst.Features.Auth.Services.LoginService;
import com.example.Linkdlyst.Features.Auth.Services.SignupService;
import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;
import com.example.Linkdlyst.Features.Auth.Services.ValidateRefreshToken;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;
    private HttpServletRequest request;
    private final JwtService jwtService;
    private final ValidateRefreshToken validateRefreshToken;

    public AuthController(SignupService _signupService, LoginService _loginService,
        HttpServletRequest request,
        JwtService _JwtService,
        ValidateRefreshToken _ValidateRefreshToken
    ) {
        signupService = _signupService;
        loginService = _loginService;
        request = request;
        jwtService = _JwtService;
        validateRefreshToken = _ValidateRefreshToken;
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalApiResponse> login(
       @Valid @RequestBody LoginRequestBody loginRequestBody) {
        LoginResponseBody loginResponse = loginService.login(loginRequestBody);

        ResponseCookie accessTokenCookie = ResponseCookie.from("user_access_token", loginResponse.getAccessToken())
        .httpOnly(true)
        .secure(false) //set to true in production
        .path("/")
        .sameSite("Lax")
        .maxAge(Duration.ofMinutes(15))
        .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", loginResponse.getRefreshToken())
        .httpOnly(true)
        .secure(false)
        .path("/")
        .sameSite("Lax")
        .maxAge(Duration.ofDays(30))
        .build();
        
        return ResponseEntity.status(200)
        .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
        .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
        .body(new GlobalApiResponse<>(true, "User logged in successfully", null));
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

    @PostMapping ("/token/refresh")
    public ResponseEntity<GlobalApiResponse> generateRefreshToken(){
        String refreshToken = request.getHeader("Refresh_Token");
        String accessToken = validateRefreshToken.createToken(refreshToken);

        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "token renewed", new LoginResponseBody(accessToken, refreshToken))
        );
    }
}
