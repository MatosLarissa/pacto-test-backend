package com.pacto.internalrecruitment.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public static boolean contains(String test) {
        for (UserRole c : UserRole.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

}
