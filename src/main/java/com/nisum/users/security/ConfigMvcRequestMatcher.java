package com.nisum.users.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Component
public class ConfigMvcRequestMatcher {
  
  @Bean
  public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector   introspector) {
      return new MvcRequestMatcher.Builder(introspector);
  }

}