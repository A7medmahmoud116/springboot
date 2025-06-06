package com.example.Asset.Tracking.System.security.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SystemConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
