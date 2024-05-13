package com.pacto.internalrecruitment.model.dtos.role;

import com.pacto.internalrecruitment.model.enums.StatusAccount;
import com.pacto.internalrecruitment.model.enums.UserRole;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleResponseDto {

    private UserRole role;

    public RoleResponseDto(UserRole role){
      this.role = role;
    }
}
