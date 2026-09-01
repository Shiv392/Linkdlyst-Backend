package com.example.Linkdlyst.Features.Auth.Dto;

public class LoginResponseBody {
    private String accessToken;
    private String refreshToken;

    public LoginResponseBody(){}

    public LoginResponseBody(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
}
