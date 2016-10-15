package com.kraluk.scf.server.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
    private static final String TEMPLATE_NAME = "mailTemplate";

    private final TemplateEngine templateEngine;

    public String getContent(String user, String message) {
        Context context = new Context();
        context.setVariable(Variable.USER, user);
        context.setVariable(Variable.MESSAGE, message);

        String content = templateEngine.process(TEMPLATE_NAME, context);

        return content;
    }

    private static class Variable {
        static final String USER = "user";
        static final String MESSAGE = "message";
    }
}