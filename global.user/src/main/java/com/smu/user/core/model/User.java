package com.smu.user.core.model;

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
@Document(collection = "user")
public class User {
    @Id
    private String userId;
    private String userCode;
    private String fullName;
    private String userName;
    private String telephone;
    private String email;
    private String memberCode;
    private String staffCode;
    private String password;
    private String roleCode;
    private String roleName;
    private String status;
    private String createdAt;
    private String createdMonth;
    private String createdYear;
    private String createdDate;
}
