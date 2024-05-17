package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.model.Feedback;
import com.pacto.internalrecruitment.model.dtos.feedback.FeedbackRequestDto;
import com.pacto.internalrecruitment.service.FeedbackService;
import com.pacto.internalrecruitment.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("admin/feedback")
@CrossOrigin("*")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllJob() {
        List<Feedback> response = feedbackService.findAllFeedback();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/apply")
    public ResponseEntity<Feedback> createFeedback(@RequestBody FeedbackRequestDto data) {
        Feedback response = feedbackService.createFeedback(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Feedback> getJobsById(@PathVariable Integer id){
        Feedback response = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/candidateId/{candidateId}")
    public ResponseEntity<List<Feedback>> getFeedbackByUserCandidateId(@PathVariable Integer candidateId){
        List<Feedback> response = feedbackService.getFeedbackByUserCandidateId(candidateId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/jobApplicationId/{jobApplicationId}")
    public ResponseEntity<Feedback> getFeedbackByJobApplicationId(@PathVariable Integer jobApplicationId){
        Feedback response = feedbackService.getFeedbackByJobApplicationId(jobApplicationId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Integer id, @RequestBody FeedbackRequestDto data) {
        Feedback response = feedbackService.updateFeedback(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
