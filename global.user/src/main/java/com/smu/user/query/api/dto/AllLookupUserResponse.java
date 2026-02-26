package com.smu.user.query.api.dto;

import com.smu.user.query.api.response.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup User Response")
public record AllLookupUserResponse(
        boolean success, List<UserResponse> user) implements Serializable {
}
