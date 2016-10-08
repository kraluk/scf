package com.kraluk.scf.server.mail;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RealMailSender implements MailSender {

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String subject, String content) {
        throw new UnsupportedOperationException("Method not implemented!");
    }
}