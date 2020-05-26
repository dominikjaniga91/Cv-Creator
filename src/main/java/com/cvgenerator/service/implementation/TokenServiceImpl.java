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
        return generateToken(user, TokenType.VERIFICATION);
    }

    @Override
    public String createPasswordResetToken(User user) {
       return generateToken(user, TokenType.PASSWORD_RESET);
    }

    private String generateToken(User user, TokenType tokenType){

        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setUser(user);
        token.setTokenType(tokenType);
        token.setExpiryDate(LocalDateTime.now());
        token.setValue(tokenValue);
        tokenRepository.save(token);

        return tokenValue;
    }
}
