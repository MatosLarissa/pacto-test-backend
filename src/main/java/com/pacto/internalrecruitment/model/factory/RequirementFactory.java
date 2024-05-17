package com.pacto.internalrecruitment.model.factory;

import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.dtos.requirement.RequirementRequestDto;

public class RequirementFactory {

    private static void setRequirementFields(Requirement requirement, Requirement requestDto) {
        if (requestDto.getRequirementName() != null) {
            requirement.setRequirementName(requestDto.getRequirementName().toUpperCase());
        }
        if (requestDto.getYearsExperience() != null) {
            requirement.setYearsExperience(requestDto.getYearsExperience().toUpperCase());
        }
    }

    public static Requirement createRequirement(Requirement requestDto) {
        Requirement newRequirement = new Requirement();
        setRequirementFields(newRequirement, requestDto);
        return newRequirement;
    }

    public static Requirement updateRequirement(Requirement existingRequirement, Requirement requestDto) {
        setRequirementFields(existingRequirement, requestDto);
        return existingRequirement;
    }
}
