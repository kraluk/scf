package com.kraluk.scf.server.mail.sender;

import com.kraluk.scf.server.core.exception.ScfRuntimeException;
import com.kraluk.scf.server.mail.conf.properties.MailProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

import static com.kraluk.scf.server.util.ApplicationProfile.PRODUCTION;

/**
 * Provides mail functionalities in the application
 *
 * @author lukasz
 */
@Service
@Profile(PRODUCTION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RealMailSender implements MailSender {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;

    @Override
    public void send(String to, String subject, String content) {
        try {
            log.info("Attempting to send a mail to '{}' with subject '{}'...", to, subject);

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setFrom(mailProperties.getFrom());
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);

            log.info("Message sended successfully!");
        } catch (Exception e) {
            throw new ScfRuntimeException("Unable to send a Mail Message!", e);
        }
    }
}