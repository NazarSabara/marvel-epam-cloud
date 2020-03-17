package com.sabara.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapping {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
