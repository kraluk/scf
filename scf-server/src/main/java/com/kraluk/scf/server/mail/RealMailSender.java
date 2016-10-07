package com.kraluk.scf.server.mail;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Provides mail functionalities in the application
 *
 * @author lukasz
 */
@Service
@Slf4j
public class RealMailSender implements MailSender {

    private final JavaMailSender mailSender;

    @Autowired
    public RealMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String to, String subject, String content) {
        throw new UnsupportedOperationException("Method not implemented!");
    }
}