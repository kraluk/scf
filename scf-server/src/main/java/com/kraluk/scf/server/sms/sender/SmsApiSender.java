package com.kraluk.scf.server.sms.sender;

import com.kraluk.scf.server.core.exception.ScfRuntimeException;
import com.kraluk.scf.server.sms.util.SmsResponseUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.exception.SmsapiException;

import static com.kraluk.scf.server.util.ApplicationProfile.PRODUCTION;

/**
 * Provides SMS sending functionalities by SmsApi in the application
 *
 * @author lukasz
 */
@Service
@Profile(PRODUCTION)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SmsApiSender implements SmsSender {
    private static final String SMS_TYPE = "ECO";

    private final SmsFactory smsFactory;

    @Override
    public String send(String to, String content) {
        try {
            log.info("Attempting to send a message to '{}'", to);

            SMSSend action = smsFactory.actionSend()
                .setText(content)
                .setTo(to)
                .setSender(SMS_TYPE);

            SendStatusResponse response = action.execute();
            String status = SmsResponseUtils.getPrettyResponse(response);

            log.info("Message sended successfully with status '{}'", status);

            return status;

        } catch (SmsapiException e) {
            throw new ScfRuntimeException("Unable to send a Text Message!", e);
        }
    }
}