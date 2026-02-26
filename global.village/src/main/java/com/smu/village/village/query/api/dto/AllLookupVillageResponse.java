package com.smu.village.village.query.api.dto;

import com.smu.village.village.query.api.response.VillageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Village Response")
public record AllLookupVillageResponse(Boolean success, List<VillageResponse> villages) implements Serializable { }
