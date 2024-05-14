package com.pacto.internalrecruitment.model.dtos.jobapplication;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobApplicationRequestDto {

    private Integer userId;

    private Integer jobId;

    public JobApplicationRequestDto(Integer userId, Integer jobId){
        this.userId = userId;
        this.jobId = jobId;
    }
}
