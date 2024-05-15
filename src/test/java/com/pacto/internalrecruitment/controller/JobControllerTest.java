package com.pacto.internalrecruitment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.dtos.job.JobResponseDto;
import com.pacto.internalrecruitment.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllJob() {
        // Given
        List<Job> jobs = Arrays.asList(new Job(), new Job());
        when(jobService.findAllJobs()).thenReturn(jobs);

        // When
        ResponseEntity<List<Job>> response = jobController.getAllJob();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobs, response.getBody());
    }

    @Test
    void testCreateJob() {
        // Given
        Job job = new Job();
        JobResponseDto jobResponseDto = new JobResponseDto(1, "Title", "Description", 1, "Status", LocalDateTime.now(), new HashSet<>());
        when(jobService.createJob(job)).thenReturn(jobResponseDto);

        // When
        ResponseEntity<?> response = jobController.crateJob(job);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobResponseDto, response.getBody());
    }

    @Test
    void testGetJobsById() {
        // Given
        Job job = new Job();
        job.setJobId(1);
        when(jobService.getJobById(1)).thenReturn(job);

        // When
        ResponseEntity<Job> response = jobController.getJobsById(1);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(job, response.getBody());
    }

    @Test
    void testGetJobByName() {
        // Given
        String jobText = "software";
        List<Job> jobs = Arrays.asList(new Job(), new Job());
        when(jobService.getJobByTitleOrDescription(jobText)).thenReturn(jobs);

        // When
        List<?> response = jobController.getJobByName(jobText);

        // Then
        assertTrue(response instanceof List);
        assertEquals(jobs, response);
    }

    @Test
    void testUpdateJob() {
        // Given
        Integer id = 1;
        Job job = new Job();
        JobResponseDto jobResponseDto = new JobResponseDto(1, "Title", "Description", 1, "Status", LocalDateTime.now(), new HashSet<>());
        when(jobService.updateJob(id, job)).thenReturn(jobResponseDto);

        // When
        ResponseEntity<?> response = jobController.updateJob(id, job);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobResponseDto, response.getBody());
    }
    @Test
    void testDeleteJob() {
        // Given
        Integer id = 1;
        doNothing().when(jobService).deleteJob(id);

        // When
        ResponseEntity<Void> response = jobController.deleteJob(id);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetJobByStatus() {
        // Given
        String status = "open";
        List<Job> jobs = Arrays.asList(new Job(), new Job());
        when(jobService.getJobByStatus(status)).thenReturn(jobs);

        // When
        ResponseEntity<List<Job>> response = jobController.getJobByStatus(status);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobs, response.getBody());
    }

    @Test
    void testGetJobByRequirement() {
        // Given
        String requirement = "java";
        List<Job> jobs = Arrays.asList(new Job(), new Job());
        when(jobService.getJobByRequirement(requirement)).thenReturn(jobs);

        // When
        ResponseEntity<List<Job>> response = jobController.getJobByRequirement(requirement);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobs, response.getBody());
    }

}
