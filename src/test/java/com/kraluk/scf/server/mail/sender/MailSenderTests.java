package com.kraluk.scf.server.mail.sender;

import com.kraluk.scf.server.util.ApplicationProfile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(ApplicationProfile.TEST)
public class MailSenderTests {

    @Autowired
    private MailSender mailSender;

    @Test
    public void testSendWithDummyValues() {
        mailSender.send("", "", "");
    }
}