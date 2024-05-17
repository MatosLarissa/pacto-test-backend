package com.pacto.internalrecruitment.model.dtos.jobapplication;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobApplicationResponseDto {
    private Integer jobApplicationId;

    private String status;

    private Integer userId;

    private Integer jobId;

    private LocalDateTime applicationDate;

    public JobApplicationResponseDto(Integer jobApplicationId, String status, Integer userId, Integer jobId, LocalDateTime applicationDate){
        this.jobApplicationId = jobApplicationId;
        this.status = status;
        this.userId = userId;
        this.jobId = jobId;
        this.applicationDate = applicationDate;

    }
}
