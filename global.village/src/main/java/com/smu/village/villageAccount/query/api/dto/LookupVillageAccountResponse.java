package com.smu.village.villageAccount.query.api.dto;

import com.smu.village.villageAccount.query.api.response.VillageAccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup Village Account Response")
public record LookupVillageAccountResponse(Boolean success, VillageAccountResponse villageAccount) implements Serializable { }
