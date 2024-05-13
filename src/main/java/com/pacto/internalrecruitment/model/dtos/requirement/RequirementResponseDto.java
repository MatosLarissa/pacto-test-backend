package com.pacto.internalrecruitment.model.dtos.requirement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequirementRequestDto {

    private String requirementName;

    private String yearsExperience;

    public RequirementRequestDto(String requirementName, String yearsExperience){
        this.requirementName = requirementName;
        this.yearsExperience = yearsExperience;
    }
}
