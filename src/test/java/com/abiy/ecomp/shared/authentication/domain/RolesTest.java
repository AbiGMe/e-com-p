package com.abiy.ecomp.shared.authentication.domain;

import com.abiy.ecomp.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class RolesTest {

    @Test
    void shouldNotHaveRoleWithoutRoles() {
        assertThat(new Roles(null).hasRole()).isFalse();
    }

    @Test
    void shouldHaveRoleWithRoles() {
        assertThat(new Roles(Set.of(Role.ADMIN)).hasRole()).isTrue();
    }

    @Test
    void shouldNotHaveNotAffectedRole() {
        assertThat(new Roles(Set.of(Role.ADMIN)).hasRole(Role.USER)).isFalse();
    }

    @Test
    void shouldHaveAffectedRole() {
        assertThat(new Roles(Set.of(Role.ADMIN)).hasRole(Role.ADMIN)).isTrue();
    }

    @Test
    void shouldStreamRoles() {
        assertThat(new Roles(Set.of(Role.ADMIN)).stream()).containsExactly(Role.ADMIN);
    }

    @Test
    void shouldGetRoles() {
        assertThat(new Roles(Set.of(Role.ADMIN)).get()).containsExactly(Role.ADMIN);
    }
}
