package com.cvgenerator.controller;

import com.cvgenerator.utils.service.implementation.SmsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class SmsController {

    private final SmsServiceImpl smsService;

    @Autowired
    public SmsController(SmsServiceImpl smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/sms")
    @ResponseStatus(HttpStatus.OK)
    public void getSms(@RequestBody String request) {
        String email= null;
        try{
            email = new ObjectMapper().readTree(request).get("email").asText();
            smsService.sendSms(email);
        }catch (Throwable throwable ){
            log.error("Cannot send SMS to user with email {}. Error: {}", email, throwable.getMessage());
        }
    }
}
