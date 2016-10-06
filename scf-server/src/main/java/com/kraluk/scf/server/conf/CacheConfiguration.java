package com.kraluk.scf.server.conf;

import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Application Caches Configuration class
 *
 * @author lukasz
 */
@Configuration
public class CacheConfiguration {
    public static final String CLIENT_CACHE = "client-cache";

    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager(CLIENT_CACHE);

        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES);

        cacheManager.setCacheBuilder(cacheBuilder);

        return cacheManager;
    }

    @Bean
    @Qualifier("rateLimiterCache")
    public Map<String, RateLimiter> rateLimiterCache() {

        Map<String, RateLimiter> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(60, TimeUnit.MINUTES)
            .<String, RateLimiter>build()
            .asMap();

        return cache;
    }
}