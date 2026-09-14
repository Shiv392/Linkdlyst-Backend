package com.example.Linkdlyst.Utils.Services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.Linkdlyst.Features.Auth.Dto.AuthUser;

@Service 
public class GetAuthUserService {

    public AuthUser getAuthUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return authUser;
    }
}
