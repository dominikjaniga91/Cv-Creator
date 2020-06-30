package com.cvgenerator.utils.service.implementation;

import com.clicksend.controllers.SMSController;
import com.clicksend.models.SmsMessageCollection;
import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.SmsToken;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.UserNotFoundException;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.implementation.SmsTokenServiceImpl;
import com.cvgenerator.utils.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.clicksend.models.SmsMessageBuilder;
import com.clicksend.models.SmsMessage;
import com.clicksend.Configuration;

import java.util.List;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    private final SmsTokenServiceImpl smsTokenService;
    private final SMSController smsController;
    private final UserRepository userRepository;
    private final Messages messages;

    @Value("${sms.message}")
    private String smsMessage;

    @Value("${sms.apiKey}")
    private String apiKey;

    @Value("${sms.username}")
    private String username;

    public SmsServiceImpl(SmsTokenServiceImpl smsTokenService,
                          UserRepository userRepository,
                          Messages messages) {
        this.smsTokenService = smsTokenService;
        this.smsController = SMSController.getInstance();
        this.userRepository = userRepository;
        this.messages = messages;
    }



    public void sendSms(String email) throws Throwable {
        setUpConfigData();
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(messages.get("user.notfound")));
        List<SmsMessage> messageList = List.of(message(user));
        SmsMessageCollection collection = new SmsMessageCollection();
        collection.setMessages(messageList);
        smsController.sendSms(collection);

    }

    private SmsMessage message(User user){
        SmsToken smsToken =  smsTokenService.createSmsToken(user);
        String message = smsMessage + smsToken.getValue();
        String country = user.getCountry().getValue();
        String phoneNumber = user.getCountry().getAreaCode() + user.getPhoneNumber();

        return new SmsMessageBuilder()
                .body(message)
                .country(country)
                .to(phoneNumber)
                .build();
    }

    private void setUpConfigData(){
        Configuration.username = username;
        Configuration.key = apiKey;
    }
}
