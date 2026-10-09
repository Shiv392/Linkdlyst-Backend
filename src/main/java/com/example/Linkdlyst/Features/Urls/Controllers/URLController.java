package com.example.Linkdlyst.Features.Urls.Controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Linkdlyst.Features.Urls.Dtos.EditUrlDtos;
import com.example.Linkdlyst.Features.Urls.Dtos.PostUrlDtos;
import com.example.Linkdlyst.Features.Urls.Dtos.URLListResponse;
import com.example.Linkdlyst.Features.Urls.Entities.UrlEntiry;
import com.example.Linkdlyst.Features.Urls.Services.URLService;
import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/v1/urls")
public class URLController {

    private final URLService urlService;

    public URLController(URLService _urlService){
        urlService = _urlService;
    }
    
    @GetMapping("")
    public ResponseEntity<GlobalApiResponse> getUrls(
        @RequestParam(defaultValue="10") int limit, 
        @RequestParam(defaultValue="0") int offset, 
        @RequestParam(defaultValue="") String search) {
        Page<UrlEntiry>urls = urlService.getUrls(limit, offset, search);

        URLListResponse response = new URLListResponse(urls.getContent(), urls.getTotalElements());

        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "Fetched", response)
        );
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<GlobalApiResponse> getMethodName(@PathVariable String shortCode) {
        Optional<UrlEntiry>urlOptional = urlService.getUrlByShortCode(shortCode);
        
        UrlEntiry urlEntity = urlOptional.get();

        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "Fetched", urlEntity.getUrl())
        );
    }

    @PostMapping("")
    public ResponseEntity<GlobalApiResponse> addUrl(
        @Valid @RequestBody PostUrlDtos requestBody) {
        boolean isAdded = urlService.addURL(requestBody);
        if(isAdded){
            return ResponseEntity.status(200)
            .body(
                new GlobalApiResponse(true, "URL added successfully", null)
            );
        }
        else{
            return ResponseEntity.status(500)
            .body(
                new GlobalApiResponse(false, "Failed to add URL", null)
            );
        }
    }
    
    @PatchMapping("")
    public ResponseEntity<GlobalApiResponse> updateUrl(@RequestBody PostUrlDtos requestBody) {
        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "Fetched", null)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> deleteUrl(@PathVariable int id) {
        boolean isDeleted = urlService.deleteURL(id);

        if(isDeleted){
            return ResponseEntity.status(200)
            .body(
                new GlobalApiResponse(true, "URL deleted successfully", null)
            );
        }
        else{
            return ResponseEntity.status(500)
            .body(
                new GlobalApiResponse(false, "Failed to delete URL", null)
            );
        }
    }
    
    @PatchMapping ("/{id}")
    public ResponseEntity<GlobalApiResponse> editUrl(@PathVariable Long id, 
        
        @Valid @RequestBody EditUrlDtos requestBody){

        boolean isEdited = urlService.editURL(requestBody, id);
        if(isEdited){
            return ResponseEntity.status(200)
            .body(
                new GlobalApiResponse(true, "URL edited successfully", null)
            );
        }

        return ResponseEntity.status(500)
            .body(
                new GlobalApiResponse(false, "Failed to edit URL", null)
        );
    }
}
