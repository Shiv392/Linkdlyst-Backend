package com.example.Linkdlyst.Features.Urls.Entities;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

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

    public Long getId(){
        return id;
    }

    public String getUrl(){
        return url;
    }
    public String getShortCode(){
        return shortCode;
    }

    public void setUrl(String _url){
        url = _url;
    }
    public void setSecurityPassword(String _securityPassword){
        securityPassword = _securityPassword;
    }
}
