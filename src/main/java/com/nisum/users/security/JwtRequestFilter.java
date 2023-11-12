package com.nisum.users.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.users.dto.ErrorResponse;
import com.nisum.users.service.JwtTokenService;
import com.nisum.users.util.Constants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtTokenService jwtTokenService;
  
  private final MvcRequestMatcher.Builder mvc;
  
  public JwtRequestFilter(JwtTokenService jwtTokenService, MvcRequestMatcher.Builder mvc) {
    this.jwtTokenService = jwtTokenService;
    this.mvc = mvc;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      final String token = request.getHeader(Constants.HEADER_AUTHORIZATION);

      if (token != null && token.startsWith(Constants.PREFIX_BEARER)) {
        String tokenWithoutBearer = token.substring(Constants.INTERGER_SEVEN);
        Authentication authentication = jwtTokenService.getAuthentication(tokenWithoutBearer);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      ErrorResponse error = ErrorResponse
          .builder()
          .message(Constants.FORBIDDEN_MESSAGE)
          .build();
      
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.setContentType("application/json");
      response.getWriter().write(new ObjectMapper().writeValueAsString(error));
      response.flushBuffer();
      
      return;
    }
    filterChain.doFilter(request, response);
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.addFilterBefore(this, UsernamePasswordAuthenticationFilter.class);
      
      http.authorizeHttpRequests(auth -> auth
          .requestMatchers(mvc.pattern(Constants.AUTH_URI)).permitAll()
          .requestMatchers(antMatcher(Constants.H2_CONSOLE)).permitAll()
          .anyRequest().authenticated());

      http.csrf(AbstractHttpConfigurer::disable);
      
      http.headers(headers -> headers
        .frameOptions(frameOptions -> frameOptions.sameOrigin()));
      
      return http.build();
  }

}