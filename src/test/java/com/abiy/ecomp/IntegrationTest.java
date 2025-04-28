package com.abiy.ecomp;

import com.abiy.ecomp.shared.authentication.infrastructure.primary.TestSecurityConfiguration;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ActiveProfiles("test")
@WithMockUser
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DisplayNameGeneration(ReplaceCamelCase.class)
@SpringBootTest(classes = {EcompApplicationApp.class, TestSecurityConfiguration.class})
@ExtendWith(KafkaTestContainerExtension.class)
public @interface IntegrationTest {
    @AliasFor(annotation = SpringBootTest.class)
    String[] properties() default {};
}
