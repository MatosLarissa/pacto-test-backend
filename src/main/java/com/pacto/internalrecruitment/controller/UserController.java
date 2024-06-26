package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.controller.util.HttpResponseCreator;
import com.pacto.internalrecruitment.model.Feedback;
import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.JobApplication;
import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationRequestDto;
import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationResponseDto;
import com.pacto.internalrecruitment.service.FeedbackService;
import com.pacto.internalrecruitment.service.JobApplicationService;
import com.pacto.internalrecruitment.service.JobService;
import com.pacto.internalrecruitment.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController extends HttpResponseCreator {


    private final UserService userService;
    private final JobApplicationService jobApplicationService;
    private final FeedbackService feedbackService;
    private final JobService jobService;

    @Autowired
    public UserController(UserService userService, JobApplicationService jobApplicationService, FeedbackService feedbackService, JobService jobService) {
        this.userService = userService;
        this.jobApplicationService = jobApplicationService;
        this.feedbackService = feedbackService;
        this.jobService = jobService;
    }

    @Operation(description = "Busca usuário por id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o usuário"),
            @ApiResponse(responseCode = "404", description = "Usuário com o id informado não existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("jobs")
    public ResponseEntity<List<Job>> getAllJob() {
        List<Job> response = jobService.findAllJobs();
        return ResponseEntity.ok(response);
    }

    @PostMapping("job/apply")
    public ResponseEntity<?> crateJob(@RequestBody JobApplicationRequestDto data) {
        JobApplicationResponseDto response = jobApplicationService.applyToJob(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/job/applied{userId}")
    public ResponseEntity<List<JobApplication>> getAllJobApplied(@PathVariable Integer userId) {
        List<JobApplication> response = jobApplicationService.getApplicationByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/jobs/{userId}")
    public ResponseEntity<List<JobApplication>> getJobByRequirement(@PathVariable Integer userId) {
        List<JobApplication> response = jobApplicationService.getApplicationByUserId(userId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("feedback/candidateId/{candidateId}")
    public ResponseEntity<List<Feedback>> getFeedbackByUserCandidateId(@PathVariable Integer candidateId){
        List<Feedback> response = feedbackService.getFeedbackByUserCandidateId(candidateId);
        return ResponseEntity.ok(response);
    }

}