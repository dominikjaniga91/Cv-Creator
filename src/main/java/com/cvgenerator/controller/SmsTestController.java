package com.cvgenerator.controller;

import com.cvgenerator.utils.service.implementation.SmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SmsTestController{

    private final SmsServiceImpl smsService;

    @Autowired
    public SmsTestController(SmsServiceImpl smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/sms")
    @ResponseStatus(HttpStatus.OK)
    public void getSms(){

        try{
            smsService.sendSms();
        }catch (Throwable throwable){
            System.out.println(throwable.getMessage());
            throwable.printStackTrace();
        }
    }
}
