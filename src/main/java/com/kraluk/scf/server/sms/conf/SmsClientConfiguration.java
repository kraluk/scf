package com.kraluk.scf.server.sms.conf;

import com.kraluk.scf.server.core.exception.ScfRuntimeException;
import com.kraluk.scf.server.sms.conf.properties.SmsClientProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.smsapi.Client;
import pl.smsapi.api.SmsFactory;

/**
 * SMS Gateway REST Client Configuration
 *
 * @author lukasz
 */
@Configuration
public class SmsClientConfiguration {

    @Bean
    public SmsFactory smsFactory(SmsClientProperties smsClientProperties) {
        SmsFactory smsFactory;

        try {
            Client client = new Client(smsClientProperties.getUsername());
            client.setPasswordHash(smsClientProperties.getPassword());

            smsFactory = new SmsFactory(client);
            return smsFactory;
        } catch (Exception e) {
            throw new ScfRuntimeException("Unable to create SmsFactory!", e);
        }
    }
}