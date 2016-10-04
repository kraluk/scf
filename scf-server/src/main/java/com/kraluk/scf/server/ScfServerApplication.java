package com.kraluk.scf.server;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Simple Communication Facade Server Main Class
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@Slf4j
public class ScfServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScfServerApplication.class, args);
        log.debug("Application initialized.");
    }
}