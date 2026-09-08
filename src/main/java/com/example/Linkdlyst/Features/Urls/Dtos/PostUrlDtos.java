package com.example.Linkdlyst.Features.Urls.Dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;

public class PostUrlDtos {

    private Long id;

    @NotNull(message = "URL is required")
    @URL(message = "URL must be valid")
    private String url;

    private String securityPassword;

    public PostUrlDtos(){}

    public PostUrlDtos(String _url, String _securityPassword){
        url = _url;
        securityPassword = _securityPassword;
    }

    public String getUrl(){
        return url;
    }
    public String getSecurityPassword(){
        return securityPassword;
    }
    public Long getId(){
        return id;
    }
}
