package com.cvgenerator.controller;

import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class MailTestController {

    private final MailServiceImpl mailService;

    @Autowired
    MailTestController(MailServiceImpl mailService) {
        this.mailService = mailService;
    }
    @RequestMapping("/mail")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail() {
        mailService.sendConfirmationEmail();
    }
}
