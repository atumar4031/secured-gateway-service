package com.attech.service;

import com.attech.repo.GatewayRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GatewayService {
    private final GatewayRepo gatewayRepo;
}
