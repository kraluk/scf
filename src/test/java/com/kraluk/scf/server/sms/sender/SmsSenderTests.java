package com.kraluk.scf.server.sms.sender;

import com.google.common.base.Strings;
import com.kraluk.scf.server.core.exception.ScfRuntimeException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsSenderTests {

    @Autowired
    private SmsSender smsSender;

    @Test
    public void testSendMessage() {
        String result = smsSender.send("0000", "0000");

        assertNotNull(result);
    }

    @Test(expected = ScfRuntimeException.class)
    public void testMessageAboveLimitWillNotBeSended() {
        String message = Strings.repeat("X", SmsSender.SMS_SIZE_LIMIT + 1);

        smsSender.send("0000000", message);
    }

    @Test(expected = ScfRuntimeException.class)
    public void testCheckMessageLimitAndThrowAnException() {
        String message = Strings.repeat("X", SmsSender.SMS_SIZE_LIMIT + 1);

        smsSender.checkMessageSize(message);
    }
}