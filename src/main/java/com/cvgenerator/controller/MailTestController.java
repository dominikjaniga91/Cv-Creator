package com.cvgenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class MailTestController {

    private final JavaMailSender javaMailSender;

    @Autowired
    MailTestController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @RequestMapping("/mail")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("dominikjaniga91@gmail.com");;
        mailMessage.setSubject("Confirmation email");
        mailMessage.setText("Please click to below link to confirm email ");
        javaMailSender.send(mailMessage);
    }
}
