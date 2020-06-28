package com.cvgenerator.utils.service.implementation;

import com.clicksend.controllers.SMSController;
import com.clicksend.models.SmsMessageCollection;
import com.cvgenerator.utils.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.clicksend.models.SmsMessageBuilder;
import com.clicksend.models.SmsMessage;
import com.clicksend.Configuration;

import java.util.List;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    SMSController smsController = SMSController.getInstance();

    public void sendSms() throws Throwable {

        Configuration.username = "dominikjaniga91@gmail.com";
        Configuration.key = "E1C79FA2-D9F3-8283-54FD-74176F747A33";
        
        List<SmsMessage> messageList = List.of(message());
        SmsMessageCollection collection = new SmsMessageCollection();
        collection.setMessages(messageList);

        smsController.sendSms(collection);

    }

    private SmsMessage message(){
        return new SmsMessageBuilder()
                .body("hehehe")
                .country("Poland")
                .to("+48881463106")
                .build();
    }
}
