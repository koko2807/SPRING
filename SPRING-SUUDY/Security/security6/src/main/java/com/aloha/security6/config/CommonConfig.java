package com.aloha.security6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class CommonConfig {
  
/**
 * 🍃 AuthenticationManager 인증 관리자 빈 등록
 * @param authenticationConfiguration
 * @return
 * @throws Exception
 */
@Bean
    public AuthenticationManager authenticationManager(
                                    AuthenticationConfiguration authenticationConfiguration ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
