package com.example.Linkdlyst.Features.Urls.Dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EditUrlDtos {
    @NotNull (message = "URL is required")
    @URL(message = "URL must be valid")
    @NotEmpty (message = "URL cannot be empty")
    private String url;


    private String securityPassword;

    public EditUrlDtos() {}

    public EditUrlDtos(String _url, String _securityPassword) {
        url = _url;
        securityPassword = _securityPassword;
    }
    public String getUrl() {
        return url;
    }
    public String getSecurityPassword() {
        return securityPassword;
    }
}
