package com.pacto.internalrecruitment.model.dtos.job;

public class JobResponseDto {

    private String title;

    private String description;

    public JobResponseDto(String title, String description){
        this.title = title;
        this.description = description;
    }
}
