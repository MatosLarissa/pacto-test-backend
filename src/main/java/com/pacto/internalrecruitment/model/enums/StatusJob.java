package com.pacto.internalrecruitment.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("pending"),
    ACTIVE("active"),
    REJECTED("rejected"),
    OPEN("open"),
    CLOSED("closed");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
