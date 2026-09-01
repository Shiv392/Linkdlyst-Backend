package com.example.Linkdlyst.Features.Auth.Services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Linkdlyst.Features.Auth.Dto.LoginRequestBody;
import com.example.Linkdlyst.Features.Auth.Dto.LoginResponseBody;
import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import com.example.Linkdlyst.Features.Auth.Repository.UserRepository;
import com.example.Linkdlyst.Utils.Exceptions.UnAuthenticatedException;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public LoginService(
        UserRepository _userRepository, 
        PasswordEncoder _passwordEncoder
        

    ) {
        userRepository = _userRepository;
        passwordEncoder = _passwordEncoder;
    }

    public LoginResponseBody login(LoginRequestBody loginRequestBody){
        String email = loginRequestBody.getEmail().trim().toLowerCase();
        String password = loginRequestBody.getPassword();

        Optional<UserEntity>user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UnAuthenticatedException("Invalid email or password");
        }

        String hashedPassword = user.get().getPassword();
        boolean isPasswordMatched = passwordEncoder.matches(password, hashedPassword);
        if (isPasswordMatched) {
            // Generate JWT tokens here
            String accessToken = "your_access_token";
            String refreshToken = "your_refresh_token";
            return new LoginResponseBody(accessToken, refreshToken);
        }

        throw new UnAuthenticatedException("Invalid email or password");
    }
}
