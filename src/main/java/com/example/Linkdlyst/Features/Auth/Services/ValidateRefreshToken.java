package com.example.Linkdlyst.Features.Auth.Services;

import org.springframework.stereotype.Service;

import com.example.Linkdlyst.Features.Auth.Dto.JwtTokenUser;
import com.example.Linkdlyst.Utils.Exceptions.UnAuthenticatedException;

@Service 
public class ValidateRefreshToken {

    private final JwtService jwtService;

    public ValidateRefreshToken(JwtService _JwtService){
        jwtService = _JwtService;
    }
    
    public String createToken(String refreshToken){
        if(refreshToken == null || !refreshToken.startsWith("Bearer ")){
            throw new UnAuthenticatedException("Please login again");
        }

        boolean isValidRefreshToken = jwtService.isTokenValid(refreshToken);
        if(!isValidRefreshToken){
            throw new UnAuthenticatedException("Please login again");
        }

        String tokenType = jwtService.getTokenType(refreshToken);
        if(!"refresh".equals(tokenType)){
            throw new UnAuthenticatedException("Pleasee login again");
        }

        Long userId = jwtService.getUserId(refreshToken);
        String email = jwtService.getEmail(refreshToken);


        String accessToken = jwtService.generateAccessToken(new JwtTokenUser(userId, email, "access"));
        return accessToken;
    }
}
