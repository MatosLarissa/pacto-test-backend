package com.pacto.internalrecruitment.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class JobAndApplicationAndFeedback {
    private JobApplication jobApplication;
    private Job job;
    private Feedback feedback;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobAndApplicationAndFeedback that = (JobAndApplicationAndFeedback) o;
        return Objects.equals(jobApplication, that.jobApplication) &&
                Objects.equals(job, that.job) &&
                Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobApplication, job, feedback);
    }
}