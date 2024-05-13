package com.pacto.internalrecruitment.model.enums;

import lombok.Getter;

@Getter
public enum StatusAccount {
    PENDING("pending"),
    ACTIVE("active"),
    REJECTED("rejected");

    private final String value;

    StatusAccount(String value) {
        this.value = value;
    }

    public static boolean contains(String test) {
        for (StatusAccount c : StatusAccount.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
