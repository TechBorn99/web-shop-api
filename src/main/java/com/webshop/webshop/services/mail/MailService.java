package com.webshop.webshop.services.mail;

import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.consts.MailConstants;
import com.webshop.webshop.utils.exceptions.types.EmailNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Async
    private void sendHtmlMail(String[] sendTo, String subject, String body) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(sendTo);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            throw new EmailNotSentException(
                    ExceptionErrorCodeType.EMAIL_NOT_SENT,
                    "Email was not sent."
            );
        }
    }

    public void composeForgotPasswordMail(String[] sendTo, String url) {
        final Context forgotPasswordContext = new Context();

        forgotPasswordContext.setVariable("url", url);

        String body = templateEngine.process(MailConstants.FILE_FORGOT_PASSWORD, forgotPasswordContext);

        this.sendHtmlMail(sendTo, MailConstants.SUBJECT_FORGOT_PASSWORD, body);
    }
}
