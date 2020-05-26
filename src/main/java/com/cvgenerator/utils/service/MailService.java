package com.cvgenerator.utils.service;

import com.cvgenerator.domain.entity.User;

public interface MailService {

    void sendConfirmationEmail(User user);

    void sendWelcomeEmail(User user);

    void sendPasswordResetEmail(User user);
}
