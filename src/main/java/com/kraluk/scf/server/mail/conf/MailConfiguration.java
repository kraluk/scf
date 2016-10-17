package com.kraluk.scf.server.mail.conf;

import com.kraluk.scf.server.mail.conf.properties.MailProperties;
import com.kraluk.scf.server.util.ApplicationConstant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static com.kraluk.scf.server.mail.conf.MailConfiguration.PropertyKeys.AUTH;
import static com.kraluk.scf.server.mail.conf.MailConfiguration.PropertyKeys.CHARSET;
import static com.kraluk.scf.server.mail.conf.MailConfiguration.PropertyKeys.DEBUG;
import static com.kraluk.scf.server.mail.conf.MailConfiguration.PropertyKeys.PROTOCOL;
import static com.kraluk.scf.server.mail.conf.MailConfiguration.PropertyKeys.SOCKET_FACTORY_CLASS;
import static com.kraluk.scf.server.mail.conf.MailConfiguration.PropertyKeys.SOCKET_FACTORY_PORT;
import static com.kraluk.scf.server.mail.conf.MailConfiguration.PropertyKeys.TLS;

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

        properties.put(PROTOCOL, mailProperties.getProtocol());
        properties.put(AUTH, mailProperties.getSmtp().isAuth());
        properties.put(TLS, mailProperties.getSmtp().isStartTlsEnable());
        properties.put(SOCKET_FACTORY_PORT, mailProperties.getPort());
        properties.put(SOCKET_FACTORY_CLASS, mailProperties.getSmtp().getSocketFactory());
        properties.put(CHARSET, ApplicationConstant.DEFAULT_ENCODING);
        properties.put(DEBUG, mailProperties.isDebug());

        sender.setJavaMailProperties(properties);

        return sender;
    }

    protected static class PropertyKeys {
        public static final String PROTOCOL = "mail.transport.protocol";
        public static final String AUTH = "mail.smtp.auth";
        public static final String TLS = "mail.smtp.starttls.enable";
        public static final String DEBUG = "mail.debug";
        public static final String CHARSET = "mail.mime.charset";
        public static final String SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
        public static final String SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    }
}