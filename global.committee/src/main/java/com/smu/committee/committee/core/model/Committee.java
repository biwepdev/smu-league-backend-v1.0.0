package com.smu.committee.committee.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "committee")
public class Committee {
    @Id
    private String committeeId;
    private String committeeCode;
    private String memberCode;
    private String memberName;
    private String memberCitizenId;
    private String villageCode;
    private String villageName;
    private String postCode;
    private String postName;
    private String state;
    private boolean enabled;
    private String createAt;
    private String createdMonth;
    private String createdYear;
    private String createdDate;
}
