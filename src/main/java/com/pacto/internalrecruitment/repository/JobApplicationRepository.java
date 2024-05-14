package com.pacto.internalrecruitment.repository;

import com.pacto.internalrecruitment.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
    List<JobApplication> findByUserIdAndJobId(Integer userId, Integer jobId);
    List<JobApplication> findByUserId(Integer userId);
    List<JobApplication> findByStatusContaining(String status);
    boolean existsByUserIdAndJobId(Integer userId, Integer jobId);
}
