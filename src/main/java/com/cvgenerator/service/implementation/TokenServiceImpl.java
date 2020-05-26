package com.cvgenerator.service.implementation;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.enums.TokenType;
import com.cvgenerator.repository.TokenRepository;
import com.cvgenerator.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String createConfirmationToken(User user) {

        String tokenValue = UUID.randomUUID().toString();

        Token token = new Token();
        token.setUser(user);
        token.setTokenType(TokenType.VERIFICATION);
        token.setExpiryDate(LocalDateTime.now());
        token.setValue(tokenValue);
        tokenRepository.save(token);

        return tokenValue;
    }

    @Override
    public String createPasswordResetToken(User user) {

        String tokenValue = UUID.randomUUID().toString();

        Token token = new Token();
        token.setUser(user);
        token.setTokenType(TokenType.PASSWORD_RESET);
        token.setExpiryDate(LocalDateTime.now());
        token.setValue(tokenValue);
        tokenRepository.save(token);

        return tokenValue;
    }
}
