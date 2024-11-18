package com.cookease.cook_ease.infraestructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("comentarioMapper")
    public ModelMapper comentarioMapper() {
        return new ModelMapper();
    }
}
