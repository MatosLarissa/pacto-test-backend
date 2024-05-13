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
@Table(name = "job")
@Entity(name = "job")
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="title")
    private String title;

    @Column(name ="description", columnDefinition = "TEXT")
    private String description;

    @Column(name ="status")
    @Enumerated(EnumType.STRING)
    private StatusJob status = StatusJob.OPEN;

    @Column(name ="creator_id")
    private String creatorId;

    @Column(name ="last_update")
    private LocalDateTime lastUpdate;

    @Column(name ="creation_date")
    private LocalDateTime creationDate;

    @ManyToMany
    @JoinTable(
            name = "job_requirement",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id", referencedColumnName = "id")
    )
    private Set<Requirement> requirements = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
