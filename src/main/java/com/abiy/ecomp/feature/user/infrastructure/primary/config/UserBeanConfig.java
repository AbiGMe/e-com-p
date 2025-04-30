package com.abiy.ecomp.feature.user.infrastructure.primary.config;

import com.abiy.ecomp.feature.user.application.UserApplicationService;
import com.abiy.ecomp.feature.user.application.impl.UserApplicationServiceImpl;
import com.abiy.ecomp.feature.user.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfig {

    @Bean
    public UserApplicationService userApplicationService(UserRepository userRepository) {
        return new UserApplicationServiceImpl(userRepository);
    }
}
