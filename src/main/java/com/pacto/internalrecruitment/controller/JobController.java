package com.pacto.internalrecruitment.controller;


import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.dtos.job.JobRequestDto;
import com.pacto.internalrecruitment.model.dtos.job.JobResponseDto;
import com.pacto.internalrecruitment.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/job")
@CrossOrigin("*")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(final JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJob() {
        List<Job> response = jobService.findAllJobs();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> crateJob(@RequestBody Job data) {
        JobResponseDto response = jobService.createJob(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Job> getJobsById(@PathVariable Integer id){
        Job response = jobService.getJobById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/jobText/{jobText}")
    public List<?> getJobByName(@PathVariable String jobText){
        List<Job> response = jobService.getJobByTitleOrDescription(jobText);
        return ResponseEntity.ok(response).getBody();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Integer id, @RequestBody Job data) {
        JobResponseDto response = jobService.updateJob(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/jobStatus/{status}")
    public ResponseEntity<List<Job>> getJobByStatus(@PathVariable String status) {
        List<Job> response = jobService.getJobByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/jobRequirement/{requirement}")
    public ResponseEntity<List<Job>> getJobByRequirement(@PathVariable String requirement) {
        List<Job> response = jobService.getJobByRequirement(requirement);
        return ResponseEntity.ok(response);
    }
}
