package com.kraluk.scf.server.rest;

import com.kraluk.scf.server.mail.MailService;
import com.kraluk.scf.server.model.RestResponse;
import com.kraluk.scf.server.model.enums.OperationStatus;
import com.kraluk.scf.server.sms.SmsService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main Facade Communication Controller
 *
 * @author lukasz
 */
@RestController
@RequestMapping("/")
@Slf4j
public class FacadeController {

    private final MailService mailService;
    private final SmsService smsService;

    @Autowired
    public FacadeController(MailService mailService, SmsService smsService) {
        this.mailService = mailService;
        this.smsService = smsService;
    }

    @RequestMapping("/mail/{to}/{message}")
    public RestResponse sendMail(@PathVariable("to") String to, @PathVariable("message") String message) {
        log.debug("Preparing to send a Mail Message to '{}'...", to);
        return new RestResponse(OperationStatus.SUCCESS, "Mail sended!");
    }

    @RequestMapping("/sms/{to}/{message}")
    public RestResponse sendSms(@PathVariable("to") String to, @PathVariable("message") String message) {
        log.debug("Preparing to send a Text Message to '{}'...", to);
        return new RestResponse(OperationStatus.SUCCESS, "SMS sended!");
    }
}