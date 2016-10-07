package com.kraluk.scf.server.mail.conf;

import com.kraluk.scf.server.mail.conf.properties.MailProperties;

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

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol", mailProperties.getProtocol());
        javaMailProperties.put("mail.smtp.auth", mailProperties.getSmtp().isAuth());
        javaMailProperties
            .put("mail.smtp.starttls.enable", mailProperties.getSmtp().isStartTlsEnable());
        javaMailProperties.put("mail.debug", mailProperties.isDebug());

        sender.setJavaMailProperties(javaMailProperties);

        return sender;
    }
}