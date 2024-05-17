package com.pacto.internalrecruitment.model.dtos.admin;


import com.pacto.internalrecruitment.model.enums.StatusAccount;
import com.pacto.internalrecruitment.model.enums.UserRole;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminRequestDto {

    private String lastName;

    private String email;

    private String password;

    private StatusAccount status;

    private YearsExperience yearsExperience;

    public AdminRequestDto(String lastName, String email, String password, StatusAccount status, YearsExperience yearsExperience, LocalDateTime lastActivity, LocalDateTime creationDate){
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}
