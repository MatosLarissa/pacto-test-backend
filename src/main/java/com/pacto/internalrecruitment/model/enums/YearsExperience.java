package com.pacto.internalrecruitment.model.enums;

import lombok.Getter;

@Getter
public enum Experience {
    ZERO_TO_ONE_YEARS("0-1 years"),
    ONE_TO_THREE_YEARS("1-3 years"),
    THREE_TO_FIVE_YEARS("3-5 years"),
    FIVE_PLUS_YEARS("5+ years");

    private final String experience;

    Experience(String experience){
        this.experience = experience;
    }

}
