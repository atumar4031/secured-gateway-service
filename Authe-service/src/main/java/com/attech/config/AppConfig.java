package com.attech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AppConfig {

    @Bean
    public RestClient initRestClient(){
        return RestClient.create();
    }
}
