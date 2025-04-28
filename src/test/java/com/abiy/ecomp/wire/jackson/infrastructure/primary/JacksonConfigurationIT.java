package com.abiy.ecomp.wire.jackson.infrastructure.primary;

import com.abiy.ecomp.IntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class JacksonConfigurationIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldHandleOptional() throws JsonProcessingException {
        Optional<String> optional = Optional.of("test");
        assertThat(objectMapper.writeValueAsString(optional)).isEqualTo("\"test\"");
    }
}
