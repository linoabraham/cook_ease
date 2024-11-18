package com.cookease.cook_ease.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Acceso: localhost:8080/swagger-ui/index.html

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("DOCUMENTACION COOK EASE")
                        .version("1.0")
                        .termsOfService("https://springdoc.org/")
                        .description("Documentación del backend de Cook Ease"));
    }
}
