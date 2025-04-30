package com.abiy.ecomp.shared.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

    ACTIVE("active"),
    INACTIVE("inactive"),
    BLOCKED("blocked"),
    PENDING("pending"),
    DELETED("deleted");

    private final String key;

    public static UserStatus of(String key) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getKey().equalsIgnoreCase(key)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid user status: " + key);
    }
}
