package com.smu.user.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "User Response")
public record UserResponse(
        String userId,
        String userCode,
        String fullName,
        String userName,
        String telephone,
        String email,
        String memberCode,
        String staffCode,
        String password,
        String roleCode,
        String roleName,
        String status,
        String createdAt,
        String createdDate
) implements Serializable {
}
