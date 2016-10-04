package com.kraluk.scf.server.rest;

import com.kraluk.scf.server.cache.CacheProvider;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple Maintenance REST Controller
 *
 * @author lukasz
 */
@RestController
@RequestMapping(path = "/maintenance")
@Slf4j
public class MaintenanceController {
    private final AtomicLong pingCounter = new AtomicLong();

    private final CacheProvider clientCache;

    @Autowired
    public MaintenanceController(CacheProvider clientCache) {
        this.clientCache = clientCache;
    }

    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>(String.format("Pong! (%s)", pingCounter.incrementAndGet()),
            HttpStatus.OK);
    }

    @RequestMapping(path = "/available-cache")
    public ResponseEntity<Collection<String>> availableCache() {
        return new ResponseEntity<>(clientCache.getAvailableCaches(), HttpStatus.OK);
    }
}