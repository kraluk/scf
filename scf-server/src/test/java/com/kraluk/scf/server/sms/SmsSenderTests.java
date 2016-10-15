package com.kraluk.scf.server.sms;

import com.kraluk.scf.server.sms.sender.SmsSender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsSenderTests {

    @Autowired
    private SmsSender smsSender;

    @Test
    public void testNothing() {
        assertTrue(smsSender != null);
    }
}