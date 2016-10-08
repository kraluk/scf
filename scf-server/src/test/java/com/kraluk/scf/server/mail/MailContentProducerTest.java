package com.kraluk.scf.server.mail;

import com.google.common.base.Strings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailContentProducerTest {

    @Autowired
    private MailContentProducer producer;

    @Test
    public void shouldProduceNotEmptyContentTest() {
        String content = producer.getContent("", "");

        assertFalse(Strings.isNullOrEmpty(content));
    }

    @Test
    public void shouldProduceFilledContentTest() {
        final String user = "test";
        final String message = "Hello World!";

        String content = producer.getContent(user, message);

        assertTrue(content.contains(user) && content.contains(message));
    }
}