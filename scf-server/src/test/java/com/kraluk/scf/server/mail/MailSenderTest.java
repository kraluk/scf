package com.kraluk.scf.server.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSenderTest {

    @Autowired
    private MailSender mailSender;

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotSendMail() {
        mailSender.send("", "", "");
    }
}