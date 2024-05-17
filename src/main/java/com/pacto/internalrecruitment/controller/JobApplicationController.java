package com.pacto.internalrecruitment.controller;

import com.pacto.internalrecruitment.model.JobApplication;
import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationRequestDto;
import com.pacto.internalrecruitment.model.dtos.jobapplication.JobApplicationResponseDto;
import com.pacto.internalrecruitment.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/application")
@CrossOrigin("*")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @Autowired
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllJob() {
        List<JobApplication> response = jobApplicationService.findAllApplications();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/apply")
    public ResponseEntity<?> crateJob(@RequestBody JobApplicationRequestDto data) {
        JobApplicationResponseDto response = jobApplicationService.applyToJob(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobApplication> getJobsById(@PathVariable Integer id){
        JobApplication response = jobApplicationService.getApplicationById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplication>> getJobByName(@PathVariable String status){
        List<JobApplication> response = jobApplicationService.getJobByStatus(status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Integer id, @RequestBody JobApplication data) {
        JobApplicationResponseDto response = jobApplicationService.updateJobApplication(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        jobApplicationService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<JobApplication>> getJobByRequirement(@PathVariable Integer userId) {
        List<JobApplication> response = jobApplicationService.getApplicationByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
