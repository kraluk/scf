package com.kraluk.scf.server.test.base;

import com.google.common.util.concurrent.RateLimiter;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Base for any REST Controller test.
 * <br/>
 * Mocks rate limiter cache related with {@link com.kraluk.scf.server.filter.RateLimiterFilter}, which is required (as a Servlet Filter) by WebMvcTests
 *
 * @author lukasz
 * @see com.kraluk.scf.server.filter.RateLimiterFilter
 * @see org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
 */
public abstract class BaseControllerTest {
    private static final int DEFAULT_RATE_LIMIT = 100;

    @MockBean
    @Qualifier("rateLimiterCache")
    protected Map<String, RateLimiter> rateLimiterCache;

    @Before
    public void setUp() {
        when(rateLimiterCache.get(any(String.class)))
            .thenReturn(RateLimiter.create(DEFAULT_RATE_LIMIT));
    }
}