package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.enums.TokenType;
import com.cvgenerator.exceptions.notfound.TokenNotFoundException;
import com.cvgenerator.repository.TokenRepository;
import com.cvgenerator.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final Messages messages;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository,
                            Messages messages) {
        this.tokenRepository = tokenRepository;
        this.messages = messages;
    }

    @Override
    public Token createConfirmationToken(User user) {
        return generateToken(user, TokenType.VERIFICATION);
    }

    @Override
    public Token createPasswordResetToken(User user) {
       return generateToken(user, TokenType.PASSWORD_RESET);
    }

    private Token generateToken(User user, TokenType tokenType){

        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setUser(user);
        token.setTokenType(tokenType);
        token.setExpiryDate(LocalDateTime.now().plusHours(24));
        token.setValue(tokenValue);
        tokenRepository.save(token);

        return token;
    }

    @Override
    public Token findTokenByValue(String value) {
        return tokenRepository.getTokenByValue(value).orElseThrow(() -> new TokenNotFoundException(messages.get("token.notfound")));
    }
}
