package com.kraluk.scf.server.sms.sender;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static com.kraluk.scf.server.util.ApplicationProfile.DEVELOPMENT;
import static com.kraluk.scf.server.util.ApplicationProfile.TEST;

/**
 * Dummy SmsSender implementation for the development and test purposes.
 * <br/>
 * Uses only *logger* for "sending" messages.
 *
 * @author lukasz
 */
@Service
@Profile({DEVELOPMENT, TEST})
@Slf4j
public class LogSmsSender implements SmsSender {

    @Override
    public void send(String to, String subject, String content) {
        log.info("Sending mail to '{}' with title '{}' and content '{}'...", to, subject, content);
    }
}