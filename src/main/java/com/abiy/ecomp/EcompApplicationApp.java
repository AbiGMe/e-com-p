package com.abiy.ecomp;

import com.abiy.ecomp.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

@EnableFeignClients
@SpringBootApplication
@ExcludeFromGeneratedCodeCoverage(reason = "Not testing logs")
public class EcompApplicationApp {

    private static final Logger log = LoggerFactory.getLogger(EcompApplicationApp.class);

    public static void main(String[] args) {
        Environment env = SpringApplication.run(EcompApplicationApp.class, args).getEnvironment();

        if (log.isInfoEnabled()) {
            log.info(ApplicationStartupTraces.of(env));
        }
    }
}
