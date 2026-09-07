package com.example.Linkdlyst.Utils.Services;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.security.SecureRandom;
import org.springframework.data.redis.core.StringRedisTemplate;

@Service
public class OtpService {
    private static final Duration OTP_EXPIRATION = Duration.ofMinutes(5);
    private final StringRedisTemplate redisTemplate;
    private final SecureRandom secureRandom;

    public OtpService(StringRedisTemplate _RedisTemplate){
        redisTemplate = _RedisTemplate;
        secureRandom = new SecureRandom();
    }

    public String generateOTP(String key){
        String value = redisTemplate.opsForValue().get(key);
        if(value !=null) return value;
        
         String otp = String.format(
            "%05d",
            secureRandom.nextInt(100000)
        );

        redisTemplate.opsForValue().set(
            key,
            otp,
            OTP_EXPIRATION
        );

        return otp;
    }

    public boolean verifyOtp(String key, String otp){
        String storedOtp = redisTemplate.opsForValue().get(key);

        if (storedOtp == null) {
            return false;
        }

        if (!storedOtp.equals(otp)) {
            return false;
        }

        redisTemplate.delete(key);

        return true;
    }
}
