package com.attech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class GatewayConfig {
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator route(RouteLocatorBuilder builder, List<ServiceConfig> serviceConfigs) {

        RouteLocatorBuilder.Builder routes = builder.routes();

        for (ServiceConfig config : serviceConfigs) {
            String serviceName = config.getServiceName();
            String basePath = config.getBasePath();
            int port = config.getPort();

            routes.route(serviceName, r -> r
                    .path(basePath + "/**")
                    .and()
                    .method("GET", "POST", "PUT", "PATCH", "DELETE")
                    .filters(f -> f.filter(filter))
                    .uri("http://localhost:" + port)
            );
        }
        return routes.build();
    }
}
