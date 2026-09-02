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
    private final JwtService jwtService;
    
    public LoginService(
        UserRepository _userRepository, 
        PasswordEncoder _passwordEncoder,
        JwtService _jwtService

    ) {
        userRepository = _userRepository;
        passwordEncoder = _passwordEncoder;
        jwtService = _jwtService;
    }

    public LoginResponseBody login(LoginRequestBody loginRequestBody){
        String email = loginRequestBody.getEmail().trim().toLowerCase();
        String password = loginRequestBody.getPassword();

        Optional<UserEntity>userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UnAuthenticatedException("Invalid email or password");
        }

        UserEntity user = userOptional.get();
        String hashedPassword = user.getPassword();
        boolean isPasswordMatched = passwordEncoder.matches(password, hashedPassword);
        if (isPasswordMatched) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return new LoginResponseBody(accessToken, refreshToken);
        }

        throw new UnAuthenticatedException("Invalid email or password");
    }
}
