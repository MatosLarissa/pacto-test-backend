package com.pacto.internalrecruitment.model.dtos.requirement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RequirementResponseDto {
    private Integer id;

    private String requirementName;

    private String yearsExperience;

    public RequirementResponseDto(Integer id, String requirementName, String yearsExperience){
        this.id = id;
        this.requirementName = requirementName;
        this.yearsExperience = yearsExperience;
    }
}
