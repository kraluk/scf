package com.kraluk.scf.server.core.filter;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RateLimiterFilterTests {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/maintenance/ping");
        when(request.getRemoteAddr()).thenReturn("test");
    }

    @Test
    public void testDoFilterWithSingleRequestWhichWontBeFiltered() throws IOException, ServletException {

        RateLimiterFilter filter = new RateLimiterFilter(new HashMap<>());

        filter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterWithMultipleRequestsWhichWillBeFiltered() throws IOException, ServletException {
        final int invocations = 10;

        RateLimiterFilter filter = new RateLimiterFilter(new HashMap<>());

        for (int i = 0; i < invocations; ++i) {
            filter.doFilter(request, response, filterChain);
        }

        verify(response, times(invocations - 1)).sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
            "Requests rate limit is provided! Allowed is only *1* operation per *1* second!");
    }
}