package com.example.Linkdlyst.Features.Auth.Controllers;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Linkdlyst.Features.Auth.Dto.LoginRequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.LoginResponseBody;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import com.example.Linkdlyst.Features.Auth.Services.LoginService;
import com.example.Linkdlyst.Features.Auth.Services.RenewToken;
import com.example.Linkdlyst.Features.Auth.Services.SignupService;
import com.example.Linkdlyst.Features.Auth.Services.ValidateRefreshToken;
import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;
import com.example.Linkdlyst.Utils.Exceptions.UnAuthenticatedException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;
    private HttpServletRequest request;
    private final ValidateRefreshToken validateRefreshToken;
    private final RenewToken renewTokenService;

    public AuthController(SignupService _signupService, LoginService _loginService,
        HttpServletRequest _request,
        ValidateRefreshToken _ValidateRefreshToken,
        RenewToken _renewTokenService
    ) {
        signupService = _signupService;
        loginService = _loginService;
        request = _request;
        validateRefreshToken = _ValidateRefreshToken;
        renewTokenService = _renewTokenService;
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
        .maxAge(Duration.ofHours(1))
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
            return ResponseEntity.ok(new GlobalApiResponse<>(true, "New user created, please login with same credentials", null));
        } else {
            return ResponseEntity.status(500).body(new GlobalApiResponse<>(false, "Failed to sign up user", null));
        }
    }

    @PostMapping ("/token/refresh")
    public ResponseEntity<GlobalApiResponse> generateRefreshToken(){
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie : cookies){
            System.out.println("Cookie: "+ cookie.getName()+ " "+ cookie.getValue());
        }

        if(cookies == null){
            throw new UnAuthenticatedException("Seesion expired, please logged in again");
        }
        String refreshToken = null;

        for(Cookie cookie : cookies){
            if("refresh_token".equals(cookie.getName())){
                refreshToken = cookie.getValue();
            }
        }

        if(refreshToken == null){
            throw new UnAuthenticatedException("Seesion expired, please logged in again");
        }
        
        String accessToken = this.renewTokenService.renewToken(refreshToken);

        ResponseCookie accessTokenCookie = ResponseCookie.from("user_access_token", accessToken)
        .httpOnly(true)
        .secure(false) //set to true in production
        .path("/")
        .sameSite("Lax")
        .maxAge(Duration.ofHours(1))
        .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
        .httpOnly(true)
        .secure(false)
        .path("/")
        .sameSite("Lax")
        .maxAge(Duration.ofDays(30))
        .build();
        
        return ResponseEntity.status(200)
        .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
        .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
        .body(new GlobalApiResponse<>(true, "Token revived successfully", null));
    }
}
