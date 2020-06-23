package com.cvgenerator.controller;

import com.cvgenerator.utils.service.implementation.SmsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@Api(tags = "Sms controller")
@RestController
@RequestMapping("/api/sms")
public class SmsTestController {

    private final SmsServiceImpl smsService;

    @Autowired
    public SmsTestController(SmsServiceImpl smsService) {
        this.smsService = smsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendSms(){
        System.out.println(" sms controller ");
        smsService.sendSms();

    }

}
