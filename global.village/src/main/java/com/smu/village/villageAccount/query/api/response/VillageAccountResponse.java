package com.smu.village.villageAccount.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Village Account response")
public record VillageAccountResponse(
        String villageAccountId,
        String villageAccountCode,
        String accountNo,
        String villageCode,
        String villageName,
        String villageAddress,
        String date,
        String status,
        String createdAt,
        String createdDate
) implements Serializable { }
