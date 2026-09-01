package com.example.Linkdlyst.Features.Auth.Services;

import org.springframework.stereotype.Service;
import com.example.Linkdlyst.Features.Auth.Configs.SecurityConfig;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class SignupService {

    private final SecurityConfig securityConfig;
    private final Logger logger = LoggerFactory.getLogger(SignupService.class);

    public SignupService(SecurityConfig _securityConfig) {
        securityConfig = _securityConfig;
    }
    
    public String signup(SignupRequestBody signupRequestBody) {
        String password = signupRequestBody.getPassword();
        String hashPassword = securityConfig.passwordEncoder().encode(password);
        logger.info("Hash password: "+hashPassword);
        return "Signup Service";
    }
}
