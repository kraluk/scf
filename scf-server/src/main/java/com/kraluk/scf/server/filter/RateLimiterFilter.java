package com.kraluk.scf.server.filter;

import com.google.common.util.concurrent.RateLimiter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Very simple Rate Limiter based on Guava's RateLimiter implementation.
 * <br/>
 * When user reaches rate limit, he receives HTTP response with 429 code.
 *
 * @author lukasz
 * @see RateLimiter
 */
@Component
@WebFilter(urlPatterns = "/*")
@Slf4j
public class RateLimiterFilter implements Filter {

    private final Map<String, RateLimiter> cache;

    @Autowired
    public RateLimiterFilter(@Qualifier("rateLimiterCache") Map<String, RateLimiter> cache) {
        this.cache = cache;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Rate Limiter Filter initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        String userIp = request.getRemoteAddr();

        if (!cache.containsKey(userIp)) {
            cache.put(userIp, RateLimiter.create(1));
            log.debug("Created RateLimiter for user '{}'.", userIp);
        }

        RateLimiter limiter = cache.get(userIp);

        if (limiter.tryAcquire()) {
            chain.doFilter(request, response);
        } else {
            log.warn("User '{}' reached rate limitation!", userIp);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
                "Rate limitation is provided! Allowed is 1 operation per second!");
        }
    }

    @Override
    public void destroy() {
        log.info("Rate Limiter Filter destroyed.");
    }
}