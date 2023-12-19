package com.attech.repo;

import com.attech.config.ServiceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GatewayRepo extends JpaRepository<ServiceConfig, UUID> {
}
