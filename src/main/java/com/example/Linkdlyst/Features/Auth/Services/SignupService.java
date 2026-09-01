package com.example.Linkdlyst.Features.Auth.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import com.example.Linkdlyst.Features.Auth.Repository.UserRepository;
import com.example.Linkdlyst.Utils.Exceptions.BadRequestException;

@Service
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(PasswordEncoder _passwordEncoder, UserRepository _userRepository) {
        passwordEncoder = _passwordEncoder;
        userRepository = _userRepository;
    }
    
    public boolean signup(SignupRequestBody signupRequestBody) {
        String email = signupRequestBody.getEmail().trim().toLowerCase();
        String password = signupRequestBody.getPassword();
        String name = signupRequestBody.getName().trim();

        boolean isUserExists = userRepository.existsByEmail(email);
        if(isUserExists) {
            throw new BadRequestException("User already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);
        userRepository.save(new UserEntity(name, email, hashedPassword));
        return true;
    }
}
