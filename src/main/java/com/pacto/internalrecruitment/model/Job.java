package com.pacto.internalrecruitment.model;

import com.pacto.internalrecruitment.model.enums.StatusAccount;
import com.pacto.internalrecruitment.model.enums.StatusJob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "job")
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="job_id")
    private Integer jobId;

    @Column(name ="title")
    private String title;

    @Column(name ="description", columnDefinition = "TEXT")
    private String description;

    @Column(name ="status")
    private String status;

    @Column(name ="creator_id")
    private Integer creatorId;

    @Column(name ="last_update")
    private LocalDateTime lastUpdate;

    @Column(name ="creation_date")
    private LocalDateTime creationDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "job_requirement",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id")
    )
    private Set<Requirement> requirements = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.status = "OPEN";
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
