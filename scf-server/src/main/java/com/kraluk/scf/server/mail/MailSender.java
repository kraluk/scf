package com.kraluk.scf.server.mail;

/**
 * Inteface for any kind of Mail Sender used in the application
 *
 * @author lukasz
 */
public interface MailSender {

    void send(String to, String subject, String content);
}
