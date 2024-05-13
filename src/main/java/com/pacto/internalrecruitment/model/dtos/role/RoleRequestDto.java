package com.pacto.internalrecruitment.model.dtos.role;


import com.pacto.internalrecruitment.model.enums.UserRole;
import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Data;

@Data
public class RoleRequestDto {

    private UserRole role;

    public RoleRequestDto(UserRole role){
      this.role = role;
    }
}
