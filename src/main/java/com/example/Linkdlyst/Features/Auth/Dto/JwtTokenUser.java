package com.example.Linkdlyst.Features.Auth.Dto;

public class JwtTokenUser {
    private Long userId;
    private String email;
    private String tokenType;


    public JwtTokenUser(){}

    public JwtTokenUser(Long _userId, String _email, String _tokenType){
        userId = _userId;
        email = _email;
        tokenType = _tokenType;
    }

    public Long getUserId(){
        return userId;
    }
    public String getEmail(){
        return email;
    }
    public String gettokenType(){
        return tokenType;
    }
}
