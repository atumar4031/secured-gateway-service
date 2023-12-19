package com.attech.config;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "gateway_config")
public class ServiceConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String serviceName;
    private String basePath;
    private String contextPath;
    private int port;
    

    public ServiceConfig(String serviceName, String basePath, String contextPath, int port) {
        this.serviceName = serviceName;
        this.basePath = basePath;
        this.contextPath = contextPath;
        this.port = port;
    }
}
