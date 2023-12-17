package com.attech.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;
}
