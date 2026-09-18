package com.example.Linkdlyst.Features.Urls.Services;

import java.util.Optional;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.example.Linkdlyst.Features.Auth.Dto.AuthUser;
import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import com.example.Linkdlyst.Features.Auth.Repository.UserRepository;
import com.example.Linkdlyst.Features.Urls.Dtos.PostUrlDtos;
import com.example.Linkdlyst.Features.Urls.Entities.UrlEntiry;
import com.example.Linkdlyst.Features.Urls.Repository.URLRepository;
import com.example.Linkdlyst.Utils.Exceptions.BadRequestException;
import com.example.Linkdlyst.Utils.Services.GetAuthUserService;
import com.example.Linkdlyst.Features.Urls.Dtos.EditUrlDtos;

@Service
public class URLService {
    private final URLRepository urlRepository;
    private final CodeService codeService;
    private final GetAuthUserService getAuthUserService;
    private final UserRepository userRepository;
    private final StringRedisTemplate stringRedisTemplate;

    public URLService(URLRepository _urlRepository, CodeService _codeService, GetAuthUserService _getAuthUserService,
            UserRepository _userRepository, StringRedisTemplate _stringRedisTemplate) {
        urlRepository = _urlRepository;
        codeService = _codeService;
        getAuthUserService = _getAuthUserService;
        userRepository = _userRepository;
        stringRedisTemplate = _stringRedisTemplate;
    }

    public List<UrlEntiry> getUrls() {
        AuthUser authUser = getAuthUserService.getAuthUser();
        int userId = authUser.userId().intValue();
        return urlRepository.findByUser_Id((long) userId);
    }

    public boolean addURL(PostUrlDtos requestBody) {
        String url = requestBody.getUrl().toLowerCase().trim();
        AuthUser authUser = getAuthUserService.getAuthUser();
        int userId = authUser.userId().intValue();

        Optional<UrlEntiry> urlExits = urlRepository.findByUser_IdAndUrl((long) userId, url);

        if (urlExits.isPresent()) {
            throw new BadRequestException("URL already exists");
        }

        String shortCode = codeService.getShortCode(25);
        Optional<UserEntity> userEntity = userRepository.findById((long) userId);
        // UserEntity user = entityManager.getReference(
        //         UserEntity.class,
        //         (long) userId);

        if (userEntity.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        urlRepository.save(new UrlEntiry(url, shortCode, null, userEntity.get()));
        return true;
    }

    public Optional<UrlEntiry> getUrlByShortCode(String shortCode){
        AuthUser authUser = getAuthUserService.getAuthUser();
        int userId = authUser.userId().intValue();

        // String cacheKey = authUser.email()+":"+shortCode;
        // String cacheURL = stringRedisTemplate.opsForValue().get(cacheKey);
        // if(cacheURL!=null){
        //     return cacheURL;
        // }

        Optional<UrlEntiry>url = urlRepository.findByUser_IdAndShortCode((long)userId, shortCode);
        if(url.isEmpty()){
            throw new BadRequestException("Invalid short code or you don't have access to this URL");
        }

        return url; 
    }

    public boolean deleteURL(int id){
        AuthUser authUser = getAuthUserService.getAuthUser();
        int userId = authUser.userId().intValue();

        Optional<UrlEntiry>isUrlExits = urlRepository.findByIdAndUser_Id((long)id, (long)userId);
        if(isUrlExits.isPresent()){
            urlRepository.deleteById((long)id);
            return true;
        }

        else{
            throw new BadRequestException("URL not found or you don't have access to this URL");
        }
    }

    public boolean editURL(EditUrlDtos requestBody, Long id){
        AuthUser authUser = getAuthUserService.getAuthUser();
        int userId = authUser.userId().intValue();

        long urlId = id;
        String newUrl = requestBody.getUrl().toLowerCase().trim();
        String securityPassword = requestBody.getSecurityPassword();

        Optional<UrlEntiry>isUrlExits = urlRepository.findByIdAndUser_Id(urlId, (long)userId);
        if(isUrlExits.isEmpty()){
            throw new BadRequestException("URL not found or you don't have access to this URL");
        }

        UrlEntiry urlEntityRow = isUrlExits.get();

        urlEntityRow.setUrl(newUrl);
        urlEntityRow.setSecurityPassword(securityPassword);
        urlRepository.save(urlEntityRow);

        return true;
    }
}
