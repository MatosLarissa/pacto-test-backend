package com.pacto.internalrecruitment.model.factory;

import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementRequestDto;

public class RequirementFactory {

    private static void setRequirementFields(Requirement requirement, RequirementRequestDto requestDto) {
        requirement.setRequirementName(requestDto.getRequirementName().toUpperCase());
        requirement.setYearsExperience(requestDto.getYearsExperience().toUpperCase());
    }

    public static Requirement createRequirement(RequirementRequestDto requestDto) {
        Requirement newRequirement = new Requirement();
        setRequirementFields(newRequirement, requestDto);
        return newRequirement;
    }

    public static Requirement updateRequirement(Requirement existingRequirement, RequirementRequestDto requestDto) {
        setRequirementFields(existingRequirement, requestDto);
        return existingRequirement;
    }
}
