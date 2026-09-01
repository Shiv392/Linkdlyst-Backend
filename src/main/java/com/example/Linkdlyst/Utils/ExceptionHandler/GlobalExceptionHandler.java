package com.example.Linkdlyst.Utils.ExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalApiResponse> handleException(Exception ex){
        return ResponseEntity.status(500)
        .body(
            new GlobalApiResponse<>(false, ex.getMessage(), null)
        );
    }
}
