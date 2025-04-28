package com.abiy.ecomp.shared.authentication.domain;

import com.abiy.ecomp.UnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class UsernameTest {

    @Test
    void shouldGetEmptyUsernameFromNullUsername() {
        assertThat(Username.of(null)).isEmpty();
    }

    @Test
    void shouldGetEmptyUsernameFromBlankUsername() {
        assertThat(Username.of(" ")).isEmpty();
    }

    @Test
    void shouldGetUsernameFromActualUsername() {
        assertThat(Username.of("user")).contains(new Username("user"));
    }

    @Test
    void shouldGetUsername() {
        assertThat(new Username("user").get()).isEqualTo("user");
    }
}
