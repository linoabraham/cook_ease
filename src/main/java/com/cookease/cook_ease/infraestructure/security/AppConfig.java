package com.cookease.cook_ease.infraestructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(List.of("HEAD","GET","POST","PUT","DELETE","PATCH"));
        config.setAllowCredentials(true);
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Cache-Control");
        config.addAllowedHeader("Content-Type");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
