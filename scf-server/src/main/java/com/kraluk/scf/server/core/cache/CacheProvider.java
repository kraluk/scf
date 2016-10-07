package com.kraluk.scf.server.core.cache;

import com.kraluk.scf.server.core.cache.conf.CacheConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Application Cache Provider
 */
@Component
public class CacheProvider {
    private final CacheManager cacheManager;

    @Autowired
    public CacheProvider(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Collection<String> getAvailableCaches() {
        return cacheManager.getCacheNames();
    }

    public Cache getClientCache() {
        return cacheManager.getCache(CacheConfiguration.CLIENT_CACHE);
    }
}