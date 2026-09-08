package com.example.Linkdlyst.Features.Urls.Services;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service 
public class CodeService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom random = new SecureRandom();

    public String getShortCode(int length){
        if(length<=0){
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }

        return code.toString();
    }
}
