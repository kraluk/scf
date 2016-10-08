package com.kraluk.scf.server.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

/**
 * Produces a shiny HTML mail content using Thymeleaf template engine.
 * <br/>
 * The mail template is located in the resources/templates directory.
 *
 * @author lukasz
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MailContentProducer {

    private final TemplateEngine templateEngine;
}