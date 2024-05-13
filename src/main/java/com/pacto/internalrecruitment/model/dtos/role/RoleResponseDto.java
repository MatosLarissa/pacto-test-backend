package com.pacto.internalrecruitment.model.dtos.role;
import lombok.Data;
import lombok.Getter;

@Data
public class RoleResponseDto {

    private Integer id;

    private String roleType;

    public RoleResponseDto(Integer id, String roleType) {
        this.id = id;
        this.roleType = roleType;
    }

}
