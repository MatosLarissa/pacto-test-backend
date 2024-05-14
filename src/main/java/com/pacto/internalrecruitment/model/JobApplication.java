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
    private String userId;

    @Column(name ="job_id")
    private String jobId;

    @Column(name ="application_date")
    private LocalDateTime applicationDate;

    @PrePersist
    protected void onCreate() {
        this.applicationDate = LocalDateTime.now();
        this.status = "OPEN";
    }

}
