package com.smu.village.core.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Get By Code Query")
public record GetByCodeQuery(
        String code
) implements Serializable {
}
