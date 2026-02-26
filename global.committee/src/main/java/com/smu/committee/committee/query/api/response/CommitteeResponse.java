package com.smu.committee.committee.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Committee Response")
public record CommitteeResponse(
        String committeeId,
        String committeeCode,
        String memberCode,
        String memberName,
        String memberCitizenId,
        String villageCode,
        String villageName,
        String postCode,
        String postName,
        String state,
        String createAt,
        String createdMonth,
        String createdYear,
        String createdDate) implements Serializable {
}
