package com.pacto.internalrecruitment.repository;

import com.pacto.internalrecruitment.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByTitleContainsIgnoreCaseOrContentContainsIgnoreCase(String title, String content);
    List<Feedback> findByUserResponseId(Integer userResponseId);
    List<Feedback> findByUserCandidateId(Integer userCandidateId);
    Optional<Feedback> findByJobApplicationId(Integer jobApplicationId);
}
