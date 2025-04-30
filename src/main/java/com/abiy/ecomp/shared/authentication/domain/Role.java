package com.abiy.ecomp.shared.authentication.domain;

import com.abiy.ecomp.shared.error.domain.Assert;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {

    VIEW_CHARGE,
    CREATE_CHARGE,
    UPDATE_CHARGE,
    CHANGE_STATUS_CHARGE,
    DELETE_CHARGE,

    VIEW_CURRENCY,
    CREATE_CURRENCY,
    UPDATE_CURRENCY,
    CHANGE_STATUS_CURRENCY,
    DELETE_CURRENCY,

    CUSTOMER,
    OPERATOR,
    AGENT,

    ADMIN,
    USER,
    ANONYMOUS,
    SYSTEM,
    UNKNOWN;

    private static final Map<String, Role> ROLES = buildRoles();

    private static Map<String, Role> buildRoles() {
        return Stream.of(values()).collect(Collectors.toUnmodifiableMap(Role::key, Function.identity()));
    }

    public static Role from(String role) {
        Assert.notBlank("role", role);

        return ROLES.getOrDefault(role.toLowerCase(), UNKNOWN);
    }

    public String key() {
        return name().toLowerCase().replace("_", "-");
    }

    public String roleName() {
        return "ROLE_%s".formatted(name().toUpperCase());
    }
}
