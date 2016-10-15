package com.kraluk.scf.server.rest;

import com.google.common.base.Throwables;
import com.kraluk.scf.server.mail.MailContentProducer;
import com.kraluk.scf.server.mail.sender.MailSender;
import com.kraluk.scf.server.model.BaseResponse;
import com.kraluk.scf.server.model.enums.OperationStatus;
import com.kraluk.scf.server.sms.sender.SmsSender;
import com.kraluk.scf.server.util.validator.EmailValidator;
import com.kraluk.scf.server.util.validator.PhoneNumberValidator;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class FacadeController {

    private final MailSender mailSender;
    private final SmsSender smsSender;

    private final MailContentProducer contentProducer;

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

        try {
            String content = contentProducer.getContent("", message);
            mailSender.send(to, "Make Things Groovy Workshops!", content);

            response = new BaseResponse(OperationStatus.SUCCESS, "Mail sended!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Something went badly wrong during sending a Mail Message!", e);
            response =
                new BaseResponse(OperationStatus.ERROR, Throwables.getRootCause(e).getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

        try {
            smsSender.send(to, message);

            response = new BaseResponse(OperationStatus.SUCCESS, "SMS sended successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Something went badly wrong during sending a Text Message!", e);
            response =
                new BaseResponse(OperationStatus.ERROR, Throwables.getRootCause(e).getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}