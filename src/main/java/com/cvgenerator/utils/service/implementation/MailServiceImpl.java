package com.cvgenerator.utils.service.implementation;

import com.cvgenerator.config.MailMessageConfig;
import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.utils.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final MailMessageConfig mail;

    @Autowired
    MailServiceImpl(JavaMailSender javaMailSender, MailMessageConfig mail) {
        this.javaMailSender = javaMailSender;
        this.mail = mail;
    }

    @Override
    public void sendConfirmationEmail(User user, Token token) {
        String message = mail.getConfirmationMessage() + "\n" + mail.getConfirmationLink() + token;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(mail.getConfirmationTitle());
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendWelcomeEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(mail.getWelcomeTitle());
        mailMessage.setText(mail.getWelcomeMessage());
        javaMailSender.send(mailMessage);
    }
}
