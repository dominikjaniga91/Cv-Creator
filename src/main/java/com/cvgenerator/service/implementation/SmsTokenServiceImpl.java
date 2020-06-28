package com.cvgenerator.service.implementation;

import com.cvgenerator.config.Messages;
import com.cvgenerator.domain.entity.SmsToken;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.notfound.SmsTokenNotFoundException;
import com.cvgenerator.exceptions.notfound.UserNotFoundException;
import com.cvgenerator.repository.SmsTokenRepository;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.SmsTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class SmsTokenServiceImpl implements SmsTokenService {

    private final SmsTokenRepository smsTokenRepository;
    private final UserRepository userRepository;
    private final Messages messages;

    @Autowired
    public SmsTokenServiceImpl(SmsTokenRepository smsTokenRepository,
                               UserRepository userRepository,
                               Messages messages) {

        this.smsTokenRepository = smsTokenRepository;
        this.userRepository = userRepository;
        this.messages = messages;
    }

    /**
     * Find user by email provided as method param. Throw an exception if user does not exist.
     * Create instance of SmsToken assigned to specific user and save to database.
     *
     * @param email existing user email
     * @return instance of SmsToken
     */

    @Override
    public SmsToken createSmsToken(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(messages.get("user.notfound")));
        SmsToken smsToken = SmsToken.builder()
                .value(tokenValue())
                .expiryDate(LocalDateTime.now().plusMinutes(1))
                .user(user)
                .build();

        return smsTokenRepository.save(smsToken);
    }

    @Override
    public SmsToken findSmsTokenByValue(String value) {
        return smsTokenRepository.getSmsTokenByValue(value).orElseThrow(() -> new SmsTokenNotFoundException(messages.get("smsToken.notfound")));
    }

    /**
     *  Create random six-digit String value of SmsToken using Streams
     * @return SmsToken value
     */

    private String tokenValue(){

        return new Random().ints()
                            .limit(6)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining());
    }
}
