package com.cookease.cook_ease.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS a todos los endpoints
                .allowedOrigins("*") // Permite todas las orígenes
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos los métodos HTTP necesarios
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(false) // No permite el uso de credenciales
                .maxAge(3600); // Tiempo máximo en segundos que el cliente puede almacenar la respuesta de preflight
    }
}