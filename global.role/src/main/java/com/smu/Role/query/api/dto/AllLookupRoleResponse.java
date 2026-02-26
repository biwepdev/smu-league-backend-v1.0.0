package com.smu.Role.query.api.dto;

import com.smu.Role.query.api.response.RoleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Post Response")
public record AllLookupRoleResponse(
        boolean success, List<RoleResponse> post) implements Serializable {
}
