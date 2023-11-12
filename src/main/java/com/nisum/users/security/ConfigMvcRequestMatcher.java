package com.nisum.users.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Get a MvcRequestMatcher.Builder to be used in request rules
 * 
 * @author Jorge Diaz
 * @version 1.0
 */
@Component
public class ConfigMvcRequestMatcher {
  
  /**
   * Get a MvcRequestMatcher.Builder to be used in request rules
   * 
   * @param introspector
   * @return MvcRequestMatcher.Builder
   */
  @Bean
  public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
      return new MvcRequestMatcher.Builder(introspector);
  }

}
