package com.pacto.internalrecruitment.model.dtos.job;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobRequestDto {
    private Integer id;

    private String title;

    private String description;

    public JobRequestDto(Integer id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
