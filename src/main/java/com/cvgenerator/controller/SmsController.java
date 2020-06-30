package com.cvgenerator.controller;

import com.cvgenerator.utils.JsonProcessingService;
import com.cvgenerator.utils.service.implementation.SmsServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"${settings.cors_origin}"})
@Api(tags = "Sms controller")
@Slf4j
@RestController
@RequestMapping("/api")
public class SmsController {

    private final SmsServiceImpl smsService;
    private final JsonProcessingService jsonProcessingService;

    @Autowired
    public SmsController(SmsServiceImpl smsService, JsonProcessingService jsonProcessingService) {
        this.smsService = smsService;
        this.jsonProcessingService = jsonProcessingService;
    }

    @ApiOperation(value = "Send SMS to user using user's email")
    @GetMapping("/sms")
    @ResponseStatus(HttpStatus.OK)
    public void getSms(@ApiParam(value = "Request with Json object that has to contain 'email' field. Example: { \"email\": \"jankowalski@gmail.com\" }")
                       @RequestBody JsonNode request) throws Throwable {
        String email =jsonProcessingService.processJsonString(request, "email");
        smsService.sendSms(email);
    }
}
