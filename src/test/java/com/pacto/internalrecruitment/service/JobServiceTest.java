package com.pacto.internalrecruitment.service;

import com.pacto.internalrecruitment.exception.ExistingException;
import com.pacto.internalrecruitment.exception.NotFoundException;
import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.User;
import com.pacto.internalrecruitment.model.dtos.job.JobResponseDto;
import com.pacto.internalrecruitment.repository.JobRepository;
import com.pacto.internalrecruitment.repository.RequirementRepository;
import com.pacto.internalrecruitment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private RequirementRepository requirementRepository;

    @Mock
    private RequirementService requirementService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllJobs() {
        // Given
        List<Job> jobs = Arrays.asList(new Job(), new Job());
        when(jobRepository.findAll()).thenReturn(jobs);

        // When
        List<Job> result = jobService.findAllJobs();

        // Then
        assertEquals(jobs.size(), result.size());
    }

    @Test
    void testGetJobById() {
        // Given
        Job job = new Job();
        job.setJobId(1);
        when(jobRepository.findById(1)).thenReturn(Optional.of(job));

        // When
        Job result = jobService.getJobById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getJobId());
    }

}
