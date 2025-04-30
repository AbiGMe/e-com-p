package com.abiy.ecomp.shared.openfeign.infrastructure.secondary;//package com.admas.betting.core.shared.openfeign.infrastructure.secondary;
//
//import com.zemen.payment.gateway.shared.customproperties.domain.CustomProperties;
//import feign.auth.BasicAuthRequestInterceptor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cloud.openfeign.FeignClientsConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//@Configuration
//@RequiredArgsConstructor
//@Import(FeignClientsConfiguration.class)
//public class SmsFeignClientConfiguration {
//
//    private final CustomProperties properties;
//
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor(properties.getSms().getUserName(), properties.getSms().getApiKey());
//    }
//}
