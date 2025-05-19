package com.auth_service.DTO;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String passwordHash;
    private String role;
}
