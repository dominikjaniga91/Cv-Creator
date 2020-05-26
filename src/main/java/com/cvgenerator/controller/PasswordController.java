package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.implementation.TokenServiceImpl;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PasswordController {

    private final TokenServiceImpl tokenService;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailServiceImpl mailService;

    @Autowired
    public PasswordController(TokenServiceImpl tokenService,
                              UserServiceImpl userService,
                              PasswordEncoder passwordEncoder,
                              MailServiceImpl mailService,
                              UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    @GetMapping("/password-reset/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void requestForPasswordReset(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        mailService.sendPasswordResetEmail(user);
    }

    @PostMapping("/new-password")
    @ResponseStatus(HttpStatus.OK)
    public void resetUserPassword(@RequestParam String tokenValue, @RequestBody String password){

        Token token = tokenService.findTokenByValue(tokenValue).orElseThrow();
        if(token != null){
            User user = token.getUser();
            user.setPassword(passwordEncoder.encode(password));
            userService.updateUser(user);
        }

    }
}
