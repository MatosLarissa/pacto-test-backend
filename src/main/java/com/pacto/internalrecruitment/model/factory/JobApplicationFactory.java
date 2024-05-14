package com.pacto.internalrecruitment.model.factory;

import com.pacto.internalrecruitment.model.JobApplication;

public class JobApplicationFactory {
    private static void setJobApplicationFields(JobApplication jobApplication, JobApplication requestDto) {
        if (requestDto.getStatus() != null) {
            jobApplication.setStatus(requestDto.getStatus().toUpperCase());
        }
        if (requestDto.getUserId() != null) {
            jobApplication.setUserId(requestDto.getUserId());
        }
        if (requestDto.getJobId() != null) {
            jobApplication.setJobId(requestDto.getJobId());
        }
    }

    public static JobApplication createJobApplication(JobApplication requestDto) {
        JobApplication newJobApplication = new JobApplication();
        setJobApplicationFields(newJobApplication, requestDto);
        return newJobApplication;
    }

    public static JobApplication updateJobApplication(JobApplication existingJobApplication, JobApplication requestDto) {
        setJobApplicationFields(existingJobApplication, requestDto);
        return existingJobApplication;
    }
}
