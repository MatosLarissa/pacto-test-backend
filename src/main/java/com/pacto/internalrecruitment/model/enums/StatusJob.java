package com.pacto.internalrecruitment.model.enums;

import lombok.Getter;

@Getter
public enum StatusJob {
    OPEN("open"),
    CLOSED("closed");

    private final String value;

    StatusJob(String value) {
        this.value = value;
    }
}
