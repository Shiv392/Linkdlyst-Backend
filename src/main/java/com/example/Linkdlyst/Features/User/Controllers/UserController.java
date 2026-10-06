package com.example.Linkdlyst.Features.User.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import com.example.Linkdlyst.Features.User.Dtos.UserDetails;
import com.example.Linkdlyst.Features.User.Services.UserService;
import com.example.Linkdlyst.Utils.ApiResponse.GlobalApiResponse;

@RestController 
@RequestMapping("/v1/user-details")
public class UserController {

    private final UserService userService;

    public UserController(UserService _userService){
        userService = _userService;
    }
    
    @GetMapping("")
    public ResponseEntity<GlobalApiResponse> UserDetails(){
        UserEntity userDetails = userService.getUserDetails();
        return ResponseEntity.status(200)
        .body(
            new GlobalApiResponse(
                true,
                "User details fetched",
                new UserDetails(userDetails.getName(), userDetails.getEmail())
            )
        );
    }

}
