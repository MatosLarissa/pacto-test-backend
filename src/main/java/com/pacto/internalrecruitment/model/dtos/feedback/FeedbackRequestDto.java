package com.pacto.internalrecruitment.model.dtos.feedback;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackRequestDto {
    private String title;
    private String content;
    private Integer userResponseId;
    private Integer userCandidateId;
    private Integer jobApplicationId;
    private String statusApplicant;

    public FeedbackRequestDto(FeedbackRequestDto feedbackRequestDto) {
        this.title = feedbackRequestDto.getTitle();
        this.content = feedbackRequestDto.getContent();
        this.userResponseId = feedbackRequestDto.getUserResponseId();
        this.userCandidateId = feedbackRequestDto.getUserCandidateId();
        this.jobApplicationId = feedbackRequestDto.getJobApplicationId();
        this.statusApplicant = feedbackRequestDto.getStatusApplicant();
    }
}
