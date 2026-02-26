package com.smu.committee.committee.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "State response")
public record StateResponse(
        String code,
        String label
) implements Serializable {
}
