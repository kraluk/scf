package com.kraluk.scf.server.sms.sender;

/**
 * Interface for any kind of SMS Sender used in the application
 *
 * @author lukasz
 */
public interface SmsSender {

    String send(String to, String content);
}