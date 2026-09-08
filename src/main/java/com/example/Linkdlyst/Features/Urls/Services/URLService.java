package com.example.Linkdlyst.Features.Urls.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Linkdlyst.Features.Urls.Dtos.PostUrlDtos;
import com.example.Linkdlyst.Features.Urls.Entities.UrlEntiry;
import com.example.Linkdlyst.Features.Urls.Repository.URLRepository;

@Service 
public class URLService {
    private final URLRepository urlRepository;
    private final CodeService codeService;

    public URLService(URLRepository _urlRepository, CodeService _codeService){
        urlRepository = _urlRepository;
        codeService = _codeService;
    }

    public boolean addURL(PostUrlDtos requestBody){
        Optional<UrlEntiry>url = urlRepository.findByUserIdAndUrl(requestBody.getId(), requestBody.getUrl());

        if(url.isPresent()) return false;

        String shortCode = codeService.getShortCode(25);
        return true;
    }
}
