package com.smu.Role.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Role Response")
public record RoleResponse(String roleId,
                           String roleCode,
                           String name,
                           String description) implements Serializable {
}
