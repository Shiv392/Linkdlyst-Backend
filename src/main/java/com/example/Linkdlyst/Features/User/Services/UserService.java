package com.example.Linkdlyst.Features.User.Services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.Linkdlyst.Features.Auth.Dto.AuthUser;
import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import com.example.Linkdlyst.Features.Auth.Repository.UserRepository;
import com.example.Linkdlyst.Utils.Exceptions.UnAuthenticatedException;
import com.example.Linkdlyst.Utils.Services.GetAuthUserService;

@Service 
public class UserService {
    
    private UserRepository userRepository;
    private GetAuthUserService authUserService;

    public UserService(UserRepository _userRepository, GetAuthUserService _authUserService){
        userRepository = _userRepository;
        authUserService = _authUserService;
    }

    public UserEntity getUserDetails(){
        AuthUser authUser = authUserService.getAuthUser();
        Long id = authUser.userId();

        Optional<UserEntity>userDetailsOptional = userRepository.findById(id);
        if(userDetailsOptional.isPresent()){
            return userDetailsOptional.get();
        }

         throw new UnAuthenticatedException("Invalid session, please logged in");
    }
}
