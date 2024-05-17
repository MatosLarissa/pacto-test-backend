package com.pacto.internalrecruitment.model.dtos.feedback;

import lombok.Data;

@Data
public class FeedbackResponseDto {
    private Integer feedbackId;
    private String title;
    private String content;
    private Integer userResponseId;
    private Integer userCandidateId;
    private Integer jobApplicationId;
    private Integer statusApplicant;

    public FeedbackResponseDto(FeedbackResponseDto feedbackResponseDto) {
        this.feedbackId = feedbackResponseDto.getFeedbackId();
        this.title = feedbackResponseDto.getTitle();
        this.content = feedbackResponseDto.getContent();
        this.userResponseId = feedbackResponseDto.getUserResponseId();
        this.userCandidateId = feedbackResponseDto.getUserCandidateId();
        this.jobApplicationId = feedbackResponseDto.getJobApplicationId();
        this.statusApplicant = feedbackResponseDto.getStatusApplicant();

    }
}
