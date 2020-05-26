package com.cvgenerator.utils.service.implementation;

import com.cvgenerator.utils.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendConfirmationEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("dominikjaniga91@gmail.com");;
        mailMessage.setSubject("Confirmation email");
        mailMessage.setText("Please click to below link to confirm email ");
        javaMailSender.send(mailMessage);
    }
}
