package com.kraluk.scf.server.sms;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Provides sending SMSs feature
 *
 * @author lukasz
 */
@Service
@Slf4j
public class SmsService {

    private final RestTemplate restTemplate;

    @Autowired
    public SmsService(@Qualifier("smsClient") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}