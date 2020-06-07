package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.User;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
        User user1 = new User(1L, "Dominik", "Janiga", "dominikjaniga91@gmail.com","ROLE_USER", "dominik123", true, LocalDateTime.now(), null, null);
        mailService.sendConfirmationEmail(user1);
    }
}
