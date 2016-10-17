package com.kraluk.scf.server.mail.conf.properties;

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
@ConfigurationProperties(prefix = "mail")
@Data
public class MailProperties {

    @NotBlank
    private String host;

    private int port;

    private String protocol;

    private String from;

    private String username;

    private String password;

    private boolean debug;

    private String title;

    @NotNull
    private Smtp smtp;

    @Data
    public static class Smtp {

        private boolean auth;

        private boolean startTlsEnable;

        private String socketFactory;
    }
}