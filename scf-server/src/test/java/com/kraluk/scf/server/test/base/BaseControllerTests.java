package com.kraluk.scf.server.test.base;

import com.google.common.util.concurrent.RateLimiter;
import com.kraluk.scf.server.core.filter.RateLimiterFilter;
import com.kraluk.scf.server.mail.MailContentProducer;
import com.kraluk.scf.server.mail.sender.MailSender;
import com.kraluk.scf.server.sms.sender.SmsSender;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Base for any REST Controller test.
 * <br/>
 * Mocks rate limiter cache related with {@link RateLimiterFilter}, which is required (as a Servlet Filter) by WebMvcTests
 *
 * @author lukasz
 * @see RateLimiterFilter
 * @see org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
 */
public abstract class BaseControllerTests {
    public static final String EXPECTED_CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final int DEFAULT_TEST_RATE_LIMIT = 100;

    @MockBean
    @Qualifier("rateLimiterCache")
    protected Map<String, RateLimiter> rateLimiterCache;

    @MockBean
    protected MailSender mailSender;

    @MockBean
    protected SmsSender smsSender;

    @MockBean
    protected MailContentProducer mailContentProducer;

    @Before
    public void setUp() {
        when(rateLimiterCache.get(any(String.class)))
            .thenReturn(RateLimiter.create(DEFAULT_TEST_RATE_LIMIT));
    }
}