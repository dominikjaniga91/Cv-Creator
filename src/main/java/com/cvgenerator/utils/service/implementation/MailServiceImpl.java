package com.cvgenerator.utils.service.implementation;

import com.cvgenerator.config.MailMessageConfig;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.service.implementation.TokenServiceImpl;
import com.cvgenerator.utils.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final MailMessageConfig mail;
    private final TokenServiceImpl tokenService;

    @Autowired
    MailServiceImpl(JavaMailSender javaMailSender,
                    MailMessageConfig mail,
                    TokenServiceImpl tokenService) {
        this.javaMailSender = javaMailSender;
        this.mail = mail;
        this.tokenService = tokenService;
    }

    @Override
    public void sendConfirmationEmail(User user) {
        String token = tokenService.createConfirmationToken(user);
        String message = mail.getConfirmationMessage() + "\n" +
                         mail.getConfirmationLink() +
                         token;

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

    @Override
    public void sendPasswordResetEmail(User user) {
        String token = tokenService.createPasswordResetToken(user);
        String message = mail.getPasswordMessage() + "\n" +
                         mail.getPasswordLink() +
                         token;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(mail.getPasswordTitle());
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}
