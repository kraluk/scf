package com.kraluk.scf.server.rest;

import com.kraluk.scf.server.mail.MailService;
import com.kraluk.scf.server.model.BaseResponse;
import com.kraluk.scf.server.model.enums.OperationStatus;
import com.kraluk.scf.server.sms.SmsService;
import com.kraluk.scf.server.util.validator.EmailValidator;
import com.kraluk.scf.server.util.validator.PhoneNumberValidator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @RequestMapping(value = "/mail/{to}/{message}", method = GET)
    public ResponseEntity<BaseResponse> sendMail(@PathVariable("to") String to,
                                                 @PathVariable("message") String message) {
        BaseResponse response;

        if (!EmailValidator.validate(to)) {
            response = new BaseResponse(OperationStatus.ERROR,
                "Not proper format of the given email address!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        log.debug("Preparing to send a Mail Message to '{}'...", to);
        response = new BaseResponse(OperationStatus.SUCCESS, "Mail sended!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/sms/{to}/{message}", method = GET)
    public ResponseEntity<BaseResponse> sendSms(@PathVariable("to") String to,
                                                @PathVariable("message") String message) {

        BaseResponse response;

        if (!PhoneNumberValidator.validate(to)) {
            response = new BaseResponse(OperationStatus.ERROR,
                "Not proper format of the given phone number!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        log.debug("Preparing to send a Text Message to '{}'...", to);
        response = new BaseResponse(OperationStatus.SUCCESS, "SMS sended!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}