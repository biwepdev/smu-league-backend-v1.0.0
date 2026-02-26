package com.smu.village.village.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Schema(name = "Village response")
public record VillageResponse(
        String villageId,
        String villageCode,
        String villageName,
        String country,
        String province,
        String commune,
        String zone,
        String streetNumber,
        String streetName,
        String email,
        String mobileNo,
        String sectorActivities,
        String status,
        String createdAt,
        String createdDate
) implements Serializable { }
