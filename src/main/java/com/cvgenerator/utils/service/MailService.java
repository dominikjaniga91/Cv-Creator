package com.cvgenerator.utils.service;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;

public interface MailService {

    void sendConfirmationEmail(User user, Token token);

    void sendWelcomeEmail(User user);
}
