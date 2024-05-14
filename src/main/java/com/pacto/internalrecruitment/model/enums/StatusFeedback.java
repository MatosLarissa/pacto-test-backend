package com.pacto.internalrecruitment.model.enums;

import lombok.Getter;

@Getter
public enum StatusFeedback {
    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private final String value;

    StatusFeedback(String value) {
        this.value = value;
    }

    public static boolean contains(String test) {
        for (StatusFeedback c : StatusFeedback.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
