package com.attech.dto;

import lombok.Builder;

@Builder
public record NinjaResponse(long id, String name, int age, String level) { }