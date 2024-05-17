package com.pacto.internalrecruitment.model.dtos.job;

import com.pacto.internalrecruitment.model.Requirement;
import com.pacto.internalrecruitment.model.enums.StatusJob;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
public class JobResponseDto {
    private Integer id;
    private String title;
    private String description;
    private Integer creatorId;
    private String status;
    private LocalDateTime lastUpdate;
    private Set<Requirement> requirements;

    public JobResponseDto(Integer id, String title, String description, Integer creatorId, String status, LocalDateTime lastUpdate, Set<Requirement> requirements) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.creatorId = creatorId;
        this.status = status;
        this.requirements = requirements;
    }
}
