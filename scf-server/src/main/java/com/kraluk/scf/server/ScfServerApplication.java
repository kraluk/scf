package com.kraluk.scf.server;

import com.kraluk.scf.server.util.Version;

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
        log.info("Simple Communication Facade '{}' initialized.", Version.VERSION);
    }
}