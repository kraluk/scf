package com.kraluk.scf.server.core;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * SCF's Bootstrap Service
 *
 * @author lukasz
 */
@Service
@Slf4j
class BootstrapService implements InitializingBean {

    private final Environment environment;

    @Autowired
    public BootstrapService(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Available profiles: '{}'", Arrays.toString(environment.getActiveProfiles()));
    }
}
