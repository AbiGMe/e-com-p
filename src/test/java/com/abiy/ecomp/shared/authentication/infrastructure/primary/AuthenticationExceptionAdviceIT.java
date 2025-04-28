package com.abiy.ecomp.shared.authentication.infrastructure.primary;

import com.abiy.ecomp.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
@AutoConfigureMockMvc
class AuthenticationExceptionAdviceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldHandleNotAuthenticatedUserException() throws Exception {
        mockMvc
            .perform(get("/api/account-exceptions/not-authenticated"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.message").value("error.http.401"))
            .andExpect(jsonPath("$.title").value("not authenticated"));
    }

    @Test
    void shouldHandleUnknownAuthenticationException() throws Exception {
        mockMvc
            .perform(get("/api/account-exceptions/unknown-authentication"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.message").value("error.http.500"))
            .andExpect(jsonPath("$.title").value("unknown authentication"));
    }
}
