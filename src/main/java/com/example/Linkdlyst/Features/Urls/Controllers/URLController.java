package com.example.Linkdlyst.Features.Urls.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Linkdlyst.Features.Urls.Dtos.PostUrlDtos;
import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/v1/url")
public class URLController {
    
    @GetMapping("")
    public ResponseEntity<GlobalApiResponse> getUrls() {
        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "Fetched", null)
        );
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<GlobalApiResponse> getMethodName(@PathVariable String shortCode) {
        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "Fetched", null)
        );
    }

    @PostMapping("")
    public ResponseEntity<GlobalApiResponse> addUrl(@RequestBody PostUrlDtos requestBody) {
        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "Fetched", null)
        );
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
        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(true, "Fetched", null)
        );
    }
    
}
