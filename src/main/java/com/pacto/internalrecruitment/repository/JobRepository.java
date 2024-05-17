package com.pacto.internalrecruitment.repository;

import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.enums.StatusJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description);
    List<Job> findByStatusContaining(String status);
    List<Job> findByRequirementsContaining(String requirementName);
}
