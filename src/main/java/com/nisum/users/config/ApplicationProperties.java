package com.nisum.users.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Class that contains properties useful for the application
 * 
 * @author Jorge Diaz
 * @version 1.0
 */
@Getter
@Setter
@Component
public class ApplicationProperties {

  @NotNull
  @Value("${application.regex.email}")
  private String emailRegex;
  
  @NotNull
  @Value("${application.regex.password}")
  private String passwordRegex;
  
  @NotNull
  @Value("${application.jwt.secret}")
  private String secretJwt;
  
  @NotNull
  @Value("${application.jwt.expire}")
  private long expireJwt;
  
}
