package com.example.Linkdlyst.Features.Auth.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Linkdlyst.Features.Auth.Dto.SignupRequestBody;
import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;
import com.example.Linkdlyst.Features.Auth.Repository.UserRepository;
import com.example.Linkdlyst.Utils.Exceptions.BadRequestException;
import com.example.Linkdlyst.Utils.Services.EmailService;
import com.example.Linkdlyst.Utils.Services.OtpService;

@Service
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;

    public SignupService(PasswordEncoder _passwordEncoder, UserRepository _userRepository
        , OtpService _OtpService, EmailService _emailEmailService
    ) {
        passwordEncoder = _passwordEncoder;
        userRepository = _userRepository;
        otpService = _OtpService;
        emailService = _emailEmailService;
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

        // String otpKey = "auth:signup:"+email;
        // String otp = otpService.generateOTP(otpKey);
        // emailService.sendOtp(email, otp);
        
        return true;
    }
}
