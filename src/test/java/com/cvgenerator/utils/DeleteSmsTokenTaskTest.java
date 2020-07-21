package com.cvgenerator.utils;

import com.cvgenerator.domain.entity.SmsToken;
import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.domain.enums.TokenType;
import com.cvgenerator.repository.SmsTokenRepository;
import com.cvgenerator.repository.TokenRepository;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.utils.scheduler.DeleteSmsTokenTask;
import com.cvgenerator.utils.scheduler.DeleteTokenTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class DeleteSmsTokenTaskTest {

    @Autowired private DeleteSmsTokenTask deleteToken;
    @Autowired private SmsTokenRepository tokenRepository;
    @Autowired private UserRepository userRepository;

    @Test
    @DisplayName("Should return optional empty after delete expired sms tokens from DB")
    void shouldReturnOptionalEmpty_afterDeleteExpiredSmsTokenFromDatabase(){
        //given
        User user = new User.UserBuilder()
                .setId(1L)
                .setFirstName("Dominik")
                .setPassword("dominik123")
                .setLastName("Janiga")
                .buildUserDto();

        SmsToken token = new SmsToken(1L, LocalDateTime.of(2020,1,1,10,10,10), user, "134625");
        userRepository.save(user);
        tokenRepository.save(token);

        //when
        deleteToken.deleteAllExpiredTokens();

        //then
        Optional<SmsToken> foundedToken = tokenRepository.getSmsTokenByValue("134625");
        Assertions.assertEquals(Optional.empty(), foundedToken);
    }
}
