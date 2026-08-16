package com.reportplus.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private Long userId;

    private String fullName;

    private String email;

    private String role;

    private String status;

    private Long organizationId;
}