package com.pacto.internalrecruitment.model;

import com.pacto.internalrecruitment.model.enums.YearsExperience;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserJobAndFeedback {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private YearsExperience yearsExperience;
    private LocalDateTime lastActivity;
    private Set<JobAndApplicationAndFeedback> jobApplicationsAndFeedback = new HashSet<>();

    @PreUpdate
    protected void onUpdate() {
        this.lastActivity = LocalDateTime.now();
    }
}