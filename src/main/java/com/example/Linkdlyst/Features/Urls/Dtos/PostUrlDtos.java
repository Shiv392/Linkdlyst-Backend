package com.example.Linkdlyst.Features.Urls.Dtos;

import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostUrlDtos {

    @NotNull(message="Name can't be empty")
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2,  message = "Enter valid name" )
    @Size (max = 50 , message =  "Name can't be more then 50 character")
    private String name;

    @NotNull(message = "URL is required")
    @URL(message = "URL must be valid")
    @NotEmpty (message = "URL cannot be empty")
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
    public String getName(){
        return name;
    }
}
