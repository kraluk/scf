package com.kraluk.scf.server.sms.conf.properties;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * Mail Configuration Properties
 *
 * @author lukasz
 */
@Configuration
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsClientProperties {

    @NotBlank
    private String host;

    private String username;

    private String password;
}