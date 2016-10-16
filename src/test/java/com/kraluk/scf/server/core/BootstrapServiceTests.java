package com.kraluk.scf.server.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapServiceTests {

    @Autowired
    private BootstrapService bootstrapService;

    @Test
    public void testAfterPropertiesSet() throws Exception {
        BootstrapService spied = spy(bootstrapService);

        spied.afterPropertiesSet();

        verify(spied).afterPropertiesSet();
    }
}