package com.example.Linkdlyst.Features.Auth.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.Linkdlyst.Features.Auth.Filters.JWTAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;

    public SecurityConfig(JWTAuthFilter _jwtAuthFilter){
        jwtAuthFilter = _jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .csrf(csrf-> csrf.disable())
        .cors(cors-> cors.disable())
        .sessionManagement(session->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth-> auth.
            requestMatchers(
                "/v1/auth/**"
            )
            .permitAll()
            .anyRequest().
            authenticated()
        )
        .addFilterBefore(
            jwtAuthFilter, 
            UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }


    //this means, when ever we ask for PasswordEncoder, always returns 
    //this BcryptPasswordEncoder with strenght 10.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
