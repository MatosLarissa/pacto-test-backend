package com.pacto.internalrecruitment.model.dtos.job;

import com.pacto.internalrecruitment.model.Requirement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
public class JobRequestDto {
    private String title;
    private String description;
    private Integer creatorId;
    private Set<Requirement> requirements;

    public JobRequestDto(Integer id, String title, String description, Integer creatorId, Set<Requirement> requirements) {
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.requirements = requirements;
    }
}
