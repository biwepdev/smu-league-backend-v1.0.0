package com.smu.village.village.query.api.dto;

import com.smu.village.village.query.api.response.VillageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Lookup Village Response")
public record LookupVillageResponse(Boolean success, VillageResponse village) implements Serializable { }
