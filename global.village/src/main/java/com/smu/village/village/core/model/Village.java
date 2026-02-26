package com.smu.village.village.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
@Schema(name = "Village response")
@Builder
@AllArgsConstructor
@Data
public class Village {
    @Id
    private String villageId;
    private String villageCode;
    private String villageName;
    private String country;
    private String province;
    private String commune;
    private String zone;
    private String streetNumber;
    private String streetName;
    private String email;
    private String mobileNo;
    private String sectorActivities;
    private String status;
    private String createdAt;
    private String createdMonth;
    private String createdYear;
    private String createdDate;
}
