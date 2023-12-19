package com.attech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public List<ServiceConfig> serviceConfigs() {
        // Create and return a list of ServiceConfig instances
        return Arrays.asList(
                new ServiceConfig("user-service", "/users","/user-service", 9094),
                new ServiceConfig("auth-service", "/auth","/auth-service", 9093),
                new ServiceConfig("project-service", "/project","/project-service", 9092),
                new ServiceConfig("ninja-service", "/ninja","/ninja-service", 9091)
        );
    }
}
