package com.smu.village.villageAccount.core.model;

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
@Schema(name = "Village Account response")
@Builder
@AllArgsConstructor
@Data
public class VillageAccount {
    @Id
    private String villageAccountId;
    private String villageAccountCode;
    private String accountNo;
    private String villageCode;
    private String villageName;
    private String villageAddress;
    private String date;
    private String status;
    private String createdAt;
    private String createdMonth;
    private String createdYear;
    private String createdDate;
}
