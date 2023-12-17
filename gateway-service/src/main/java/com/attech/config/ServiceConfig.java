package com.attech.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceConfig {
    private String serviceName;
    private String basePath;
    private int port;
}
