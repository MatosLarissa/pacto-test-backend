package com.pacto.internalrecruitment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "job_application")
@NoArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="job_application_id")
    private Integer jobApplicationId;

    @Column(name ="status")
    private String status;

    @Column(name ="user_id")
    private Integer userId;

    @Column(name ="job_id")
    private Integer jobId;

    @Column(name ="application_date")
    private LocalDateTime applicationDate;

    public JobApplication(JobApplication jobApplication) {
        this.jobApplicationId = jobApplication.jobApplicationId;
        this.status = jobApplication.status;
        this.userId = jobApplication.userId;
        this.jobId = jobApplication.jobId;
        this.applicationDate = jobApplication.applicationDate;
    }

    @PrePersist
    protected void onCreate() {
        this.applicationDate = LocalDateTime.now();
        this.status = "PENDING";
    }

}
