package be.kdg.prog6.storeBoundedContext.adapters.out.web;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class GameWebClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof Jwt jwt) {
                String token = jwt.getTokenValue();
                requestTemplate.header("Authorization", "Bearer " + token);
            } else {
                throw new IllegalStateException("No JWT token found in Security Context");
            }
        };
    }
}
