package com.kraluk.scf.server.mail;

import com.kraluk.scf.server.mail.conf.properties.MailProperties;
import com.kraluk.scf.server.util.ApplicationConstant;
import com.kraluk.scf.server.util.Version;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

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
    private final MailProperties mailProperties;

    public String getContent(String user, String message) {
        String id = UUID.randomUUID().toString();

        Context context = new Context();
        context.setVariable(Variable.USER, user);
        context.setVariable(Variable.MESSAGE, message);
        context.setVariable(Variable.ID, id);
        context.setVariable(Variable.VERSION, Version.VERSION);
        context.setLocale(ApplicationConstant.DEFAULT_LOCALE);

        String content = templateEngine.process(TEMPLATE_NAME, context);

        log.info("Created content for a mail message with id '{}.'", id);
        return content;
    }

    public String getDefaultTitle() {
        return mailProperties.getTitle();
    }

    private static class Variable {
        private static final String USER = "user";
        private static final String MESSAGE = "message";
        private static final String ID = "id";
        private static final String VERSION = "version";
    }
}