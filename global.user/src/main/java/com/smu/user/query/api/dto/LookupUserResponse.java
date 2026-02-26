package com.smu.user.query.api.dto;

import com.smu.user.query.api.response.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup User Response")
public record LookupUserResponse(
        boolean success, UserResponse userResponse) implements Serializable {
}
