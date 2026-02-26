package com.smu.committee.committee.core.query;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Get by code query")
public record GetByCodeQuery(String code) implements Serializable {
}
