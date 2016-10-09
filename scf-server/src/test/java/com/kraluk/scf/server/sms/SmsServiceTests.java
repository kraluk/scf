package com.kraluk.scf.server.sms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsServiceTests {

    @Autowired
    private SmsService smsService;

    @Test
    public void dummyTest() {
        assertTrue(smsService != null);
    }
}