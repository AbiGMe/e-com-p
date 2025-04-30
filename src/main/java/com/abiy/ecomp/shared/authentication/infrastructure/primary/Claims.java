package com.abiy.ecomp.shared.authentication.infrastructure.primary;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

final class Claims {

    static final String CLAIMS_NAMESPACE = "https://admas-betting.io/";

    private Claims() {
    }

    static List<GrantedAuthority> extractAuthorityFromClaims(Map<String, Object> claims) {
        return mapRolesToGrantedAuthorities(getRolesFromClaims(claims));
    }

    @SuppressWarnings("unchecked")
    private static Collection<String> getRolesFromClaims(Map<String, Object> claims) {

        return ((Map<String, Object>) claims.getOrDefault("resource_access", new HashMap<>()))
            .values()
            .stream()
            .map(a -> (Map<String, List<String>>) a)
            .map(Map::values)
            .reduce(new ArrayList<>(), (first, second) -> {
                first.addAll(second);
                return first;
            })
            .stream()
            .reduce(new ArrayList<>(), (first, second) -> {
                first.addAll(second);
                return first;
            });
    }

    @SuppressWarnings("java:S6204")
    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(Collection<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
