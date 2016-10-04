package com.kraluk.scf.server.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * JavaMail Client Configuration
 *
 * @author lukasz
 */
@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSender sender = new JavaMailSenderImpl();

        return sender;
    }
}