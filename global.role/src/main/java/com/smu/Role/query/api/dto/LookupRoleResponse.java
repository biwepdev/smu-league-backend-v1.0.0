package com.smu.Role.query.api.dto;

import com.smu.Role.query.api.response.RoleResponse;

import java.io.Serializable;

public record LookupRoleResponse(
        boolean success, RoleResponse roleResponse) implements Serializable {
}
