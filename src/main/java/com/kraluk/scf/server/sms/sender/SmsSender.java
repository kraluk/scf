package com.kraluk.scf.server.sms.sender;

import com.kraluk.scf.server.core.exception.ScfRuntimeException;

/**
 * Interface for any kind of SMS Sender used in the application
 *
 * @author lukasz
 */
public interface SmsSender {

    int SMS_SIZE_LIMIT = 160;

    String send(String to, String content);

    default void checkMessageSize(String text) {
        if (text.length() > SMS_SIZE_LIMIT) {
            throw new ScfRuntimeException("Exceeded character limit (160) per message!");
        }
    }
}