package com.kraluk.scf.server.rest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>(String.format("Pong! (%s)", pingCounter.incrementAndGet()),
            HttpStatus.OK);
    }
}