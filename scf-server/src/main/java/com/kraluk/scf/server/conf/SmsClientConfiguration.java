package com.kraluk.scf.server.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * SMS Gateway REST Client Configuration
 *
 * @author lukasz
 */
@Configuration
public class SmsClientConfiguration {

    @Bean
    @Qualifier("smsClient")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}