package com.pacto.internalrecruitment.model.dtos.role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleRequestDto {

    private String roleType;

    public RoleRequestDto(String roleType){
        this.roleType = roleType;
    }

}

