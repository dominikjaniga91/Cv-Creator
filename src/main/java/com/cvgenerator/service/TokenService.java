package com.cvgenerator.service;

import com.cvgenerator.domain.entity.User;

public interface TokenService {

    String createConfirmationToken(User user);

    String createPasswordResetToken(User user);
}
