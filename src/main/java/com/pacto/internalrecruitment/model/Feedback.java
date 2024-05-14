package com.pacto.internalrecruitment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "feedback")
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="feedback_id")
    private Integer feedbackId;

    @Column(name ="title")
    private String title;

    @Column(name ="content", columnDefinition = "TEXT")
    private String content;

    @Column(name ="user_response_id")
    private Integer userResponseId;

    @Column(name ="user_candidate_id")
    private Integer userCandidateId;

    @Column(name ="job_application_id")
    private Integer jobApplicationId;

    @Column(name ="response_date")
    private LocalDateTime responseDate;

    @PrePersist
    protected void onCreate() {
        this.responseDate = LocalDateTime.now();
    }
}
