package com.example.Linkdlyst.Features.Urls.Entities;

import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name =  "URLs",
    indexes = {
        @Index(name="short_code_idx", columnList = "shortCode", unique = true)
    }
)
public class UrlEntiry {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable =  false)
    private String url;

    @Column(nullable =  false, length = 25)
    private String shortCode;

    @Column(nullable = true)
    private String securityPassword;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    public UrlEntiry(){}

    public UrlEntiry(String _url, String _shortCode, String _securityPassword, UserEntity _userEntity){
        url = _url;
        shortCode = _shortCode;
        securityPassword = _securityPassword;
        user = _userEntity;
    }

    public String getUrl(){
        return url;
    }
    public String getShortCode(){
        return shortCode;
    }
    public String getSecurityPassword(){
        return securityPassword;
    }
}
