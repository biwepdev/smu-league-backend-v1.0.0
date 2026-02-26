package com.smu.village.villageAccount.query.api.dto;

import com.smu.village.villageAccount.query.api.response.VillageAccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Village Account Response")
public record AllLookupVillageAccountResponse(Boolean success, List<VillageAccountResponse> villageAccounts) implements Serializable { }
