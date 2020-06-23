package com.cvgenerator.utils.service.implementation;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
public class SmsServiceImpl {

    public void sendSms() {

        // Find your Account Sid and Token at twilio.com/user/account
        String ACCOUNT_SID ="";
        String AUTH_TOKEN = "";


            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber("+48881463106"),
                    new PhoneNumber("+12565379588"),
                    "This is the ship that made the Kessel Run in fourteen parsecs?").create();

            System.out.println(message.getSid());
    }
}
