package com.abiy.ecomp.shared.audit.secondary;

import com.abiy.ecomp.shared.authentication.application.AuthenticatedUser;
import com.abiy.ecomp.shared.authentication.domain.Role;
import com.abiy.ecomp.shared.authentication.domain.Username;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of(AuthenticatedUser.optionalUsername()
            .map(Username::username)
            .orElse(Role.SYSTEM.key()));
    }
}
