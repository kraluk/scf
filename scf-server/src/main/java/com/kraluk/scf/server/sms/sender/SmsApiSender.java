package com.kraluk.scf.server.sms.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import pl.smsapi.api.SmsFactory;

import static com.kraluk.scf.server.util.ApplicationProfile.NOT_TEST;

/**
 * Provides SMS sending functionalities by SmsApi in the application
 *
 * @author lukasz
 */
@Service
@Profile(NOT_TEST)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SmsApiSender implements SmsSender {

    private final SmsFactory smsFactory;

    @Override
    public void send(String to, String subject, String content) {

    }
}