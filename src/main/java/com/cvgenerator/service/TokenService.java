package com.cvgenerator.service;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;

import java.util.Optional;

public interface TokenService {

    String createConfirmationToken(User user);

    String createPasswordResetToken(User user);

    Optional<Token> findTokenByValue(String value);
}
