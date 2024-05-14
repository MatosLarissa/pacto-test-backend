package com.pacto.internalrecruitment.model.factory;

import com.pacto.internalrecruitment.model.Job;

public class JobFactory {
    private static void setJobFields(Job job, Job requestDto) {
        if (requestDto.getStatus() != null) {
            job.setStatus(requestDto.getStatus());
        }
        if (requestDto.getTitle() != null) {
            job.setTitle(requestDto.getTitle());
        }
        if (requestDto.getDescription() != null) {
            job.setDescription(requestDto.getDescription());
        }
        if (requestDto.getStatus() != null) {
            job.setStatus(requestDto.getStatus().toUpperCase());
        }
        if (requestDto.getRequirements() != null) {
            job.setRequirements(requestDto.getRequirements());
        }
    }

    public static Job createJob(Job requestDto) {
        Job newJob = new Job();
        setJobFields(newJob, requestDto);
        return newJob;
    }

    public static Job updateJob(Job existingJob, Job requestDto) {
        setJobFields(existingJob, requestDto);
        return existingJob;
    }
}
