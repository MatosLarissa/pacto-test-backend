package com.pacto.internalrecruitment.repository;

import com.pacto.internalrecruitment.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementRepository  extends JpaRepository<Requirement, Integer> {
    List<Requirement> findByRequirementName(String requirementName);
    List<Requirement> findByYearsExperienceContaining(String yearsExperience);
    List<Requirement> findByRequirementNameContaining(String requirementName);
}
