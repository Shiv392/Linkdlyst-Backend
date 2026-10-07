package com.example.Linkdlyst.Features.Auth.Services;

import org.springframework.stereotype.Service;

import com.example.Linkdlyst.Utils.Exceptions.UnAuthenticatedException;

@Service 
public class RenewToken {
    private ValidateRefreshToken validateRefreshToken; 

    public RenewToken(ValidateRefreshToken _valiRefreshToken){
        validateRefreshToken = _valiRefreshToken;
    }

    public String renewToken(String refreshToken){
        String accessToken = this.validateRefreshToken.createToken(refreshToken);
        if(accessToken == null){
            throw new UnAuthenticatedException("Session has beeb expired, please logged in again");
        }

        return accessToken;
    }
}
