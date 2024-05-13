package com.pacto.internalrecruitment.repository;

import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByTitle(String title);
    Optional<Job> findByDescription(String description);
    Optional<Job> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description);
    Optional<Job> findByStatusContaining(String status);
    Optional<Job> findByRequirementsContaining(Requirement Requirement);
}
