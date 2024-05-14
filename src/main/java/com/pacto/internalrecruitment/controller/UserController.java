package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.controller.util.HttpResponseCreator;
import com.pacto.internalrecruitment.model.Feedback;
import com.pacto.internalrecruitment.model.JobApplication;
import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationRequestDto;
import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationResponseDto;
import com.pacto.internalrecruitment.service.FeedbackService;
import com.pacto.internalrecruitment.service.JobApplicationService;
import com.pacto.internalrecruitment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController extends HttpResponseCreator {


    private final UserService userService;
    private final JobApplicationService jobApplicationService;
    private final FeedbackService feedbackService;

    @Autowired
    public UserController(UserService userService, JobApplicationService jobApplicationService, FeedbackService feedbackService) {
        this.userService = userService;
        this.jobApplicationService = jobApplicationService;
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (!userService.findUserById(id).getUserId().equals(user.getUserId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("job/apply")
    public ResponseEntity<?> crateJob(@RequestBody JobApplicationRequestDto data) {
        JobApplicationResponseDto response = jobApplicationService.applyToJob(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("jobs/")
    public ResponseEntity<List<JobApplication>> getAllJob() {
        List<JobApplication> response = jobApplicationService.findAllApplications();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/job/{userId}")
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