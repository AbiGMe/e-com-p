package com.abiy.ecomp.shared.authentication.domain;

import com.abiy.ecomp.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class RoleTest {

    @Test
    void shouldGetRoleKey() {
        assertThat(Role.ADMIN.key()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    void shouldConvertUnknownRoleToUnknownRole() {
        assertThat(Role.from("ROLE_DUMMY")).isEqualTo(Role.UNKNOWN);
    }

    @Test
    void shouldConvertFromRole() {
        assertThat(Role.from("ROLE_ADMIN")).isEqualTo(Role.ADMIN);
    }
}
