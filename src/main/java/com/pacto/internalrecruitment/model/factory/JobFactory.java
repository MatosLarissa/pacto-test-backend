package com.pacto.internalrecruitment.model.factory;

import com.pacto.internalrecruitment.model.Job;
import com.pacto.internalrecruitment.model.dtos.job.JobRequestDto;

public class JobFactory {
    private static void setJobFields(Job job, JobRequestDto requestDto) {
        job.setTitle(requestDto.getTitle());
        job.setDescription(requestDto.getDescription());
    }

    public static Job createJob(JobRequestDto requestDto) {
        Job newJob = new Job();
        setJobFields(newJob, requestDto);
        return newJob;
    }

    public static Job updateJob(Job existingJob, JobRequestDto requestDto) {
        setJobFields(existingJob, requestDto);
        return existingJob;
    }
}
