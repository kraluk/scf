package com.kraluk.scf.server.core.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheProviderTests {

    @Autowired
    private CacheProvider cacheProvider;

    @Test
    public void testGetAvailableCaches() {
        int size = cacheProvider.getAvailableCaches().size();

        assertTrue(size > 0);
    }

    @Test
    public void testGetClientCacheWithTestValuesAndPutValuesInCache() {
        final String key = "test_key";
        final String value = "test_value";

        cacheProvider.getClientCache().put(key, value);

        assertEquals(value, String.valueOf(cacheProvider.getClientCache().get(key).get()));
    }
}