package com.cms.application.security;

import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApiSecurityConfig {

    /**
     * jwt secret key
     * genertate by: openssl rand -base64 32
     * stored in config/local/application.properties
     */
    @Value("${app.auth.secret}")
    private String authSecret;

    @Bean
    @Order(SecurityProperties.DEFAULT_FILTER_ORDER - 10)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**");
        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .csrf(csrf -> csrf.disable());
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.decoder(jwtDecoder())));

        return http.build();
    }

    private JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withSecretKey(new SecretKeySpec(Base64.getDecoder().decode(authSecret), JwsAlgorithms.HS256)).build();
    }
}