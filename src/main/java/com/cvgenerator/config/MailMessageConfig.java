package com.cvgenerator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MailMessageConfig {

    @Value("${mail.confirmation.title}")
    String confirmationTitle;

    @Value("${mail.confirmation.message}")
    String confirmationMessage;

    @Value("${mail.confirmation.link}")
    String confirmationLink;

    @Value("${mail.welcome.title}")
    String welcomeTitle;

    @Value("${mail.welcome.message}")
    String welcomeMessage;

    @Value("${mail.password.title}")
    String passwordTitle;

    @Value("${mail.password.message}")
    String passwordMessage;

    @Value("${mail.password.link}")
    String passwordLink;

}
