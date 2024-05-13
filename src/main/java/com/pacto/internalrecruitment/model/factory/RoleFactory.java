package com.pacto.internalrecruitment.model.factory;

import com.pacto.internalrecruitment.model.Role;
import com.pacto.internalrecruitment.model.dtos.role.RoleRequestDto;

public class RoleFactory {

    public static Role createRole(RoleRequestDto requestDto) {

        Role newRole = new Role();
        newRole.setRoleType(String.valueOf(requestDto.getRole()));
        return newRole;
    }
}
