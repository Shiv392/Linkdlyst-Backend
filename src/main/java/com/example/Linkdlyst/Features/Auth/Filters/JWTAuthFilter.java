package com.example.Linkdlyst.Features.Auth.Filters;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.Linkdlyst.Features.Auth.Dto.AuthUser;
import com.example.Linkdlyst.Features.Auth.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JWTAuthFilter(JwtService _jwtService){
        jwtService = _jwtService;
    }
    
    @Override 
    protected  void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain )   throws ServletException, IOException{
      String authHeader = request.getHeader("Authorization");

      if(authHeader == null || !authHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
      }

      String token = authHeader.substring(7);
      if(!jwtService.isTokenValid(token)){
        filterChain.doFilter(request, response);
        return;
      }

      Long userId = jwtService.getUserId(token);
      String userEmail = jwtService.getEmail(token);

      AuthUser authUser = new AuthUser(userId, userEmail);

      //telling spring security that this request is valid
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        authUser,
        null, 
        Collections.emptyList()
        );

      //set authenticiaont object value
      SecurityContextHolder.getContext().setAuthentication(authentication);
      
      filterChain.doFilter(request, response);
    }
}
