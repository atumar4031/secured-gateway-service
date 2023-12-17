package com.attech.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;
}
