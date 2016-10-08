package com.kraluk.scf.server.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailContentProducerTest {

    @Autowired
    private MailContentProducer producer;

    @Test
    public void dummyTest() {
        assertTrue(producer != null);
    }
}