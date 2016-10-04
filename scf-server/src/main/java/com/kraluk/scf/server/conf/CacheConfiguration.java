package com.kraluk.scf.server.conf;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Cache<String, String> testCache() {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            public String load(String key) throws Exception {
                return "test";
            }
        };

        return CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .weakKeys()
            .initialCapacity(10)
            .recordStats()
            .build(loader);
    }
}