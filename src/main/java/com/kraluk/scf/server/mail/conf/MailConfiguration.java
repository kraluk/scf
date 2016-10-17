package com.kraluk.scf.server.mail.conf;

import com.kraluk.scf.server.mail.conf.properties.MailProperties;
import com.kraluk.scf.server.util.ApplicationConstant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * JavaMail Client Configuration
 *
 * @author lukasz
 */
@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender javaMailSender(MailProperties mailProperties) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost(mailProperties.getHost());
        sender.setPort(mailProperties.getPort());
        sender.setUsername(mailProperties.getUsername());
        sender.setPassword(mailProperties.getPassword());
        sender.setDefaultEncoding(ApplicationConstant.DEFAULT_ENCODING);

        Properties properties = new Properties();

        properties.put(PropertyKeys.PROTOCOL, mailProperties.getProtocol());
        properties.put(PropertyKeys.AUTH, mailProperties.getSmtp().isAuth());
        properties.put(PropertyKeys.TLS, mailProperties.getSmtp().isStartTlsEnable());
        properties.put(PropertyKeys.SOCKET_FACTORY_PORT, mailProperties.getPort());
        properties.put(PropertyKeys.SOCKET_FACTORY_CLASS, mailProperties.getSmtp().getSocketFactory());
        properties.put(PropertyKeys.CHARSET, ApplicationConstant.DEFAULT_ENCODING);
        properties.put(PropertyKeys.DEBUG, mailProperties.isDebug());

        sender.setJavaMailProperties(properties);

        return sender;
    }

    private static class PropertyKeys {
        private static final String PROTOCOL = "mail.transport.protocol";
        private static final String AUTH = "mail.smtp.auth";
        private static final String TLS = "mail.smtp.starttls.enable";
        private static final String DEBUG = "mail.debug";
        private static final String CHARSET = "mail.mime.charset";
        private static final String SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
        private static final String SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    }
}