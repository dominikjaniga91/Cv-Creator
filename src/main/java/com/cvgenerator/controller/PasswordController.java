package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.implementation.TokenServiceImpl;
import com.cvgenerator.service.implementation.UserServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Api(tags = "Password controller")
@RestController
@RequestMapping("/api")
public class PasswordController {

    private final TokenServiceImpl tokenService;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final MailServiceImpl mailService;

    @Autowired
    public PasswordController(TokenServiceImpl tokenService,
                              UserServiceImpl userService,
                              MailServiceImpl mailService,
                              UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Request for user password reset - send email to user with password reset link")
    @GetMapping("/password-reset/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void requestForPasswordReset(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        mailService.sendPasswordResetEmail(user);
    }

    @ApiOperation(value = "Reset user password")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Your password has been changed"),
            @ApiResponse(code=400, message = "Reset link is not valid"),
            @ApiResponse(code=401, message = "Reset link expired")
    })
    @PostMapping("/new-password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> resetUserPassword(@RequestParam("value") String tokenValue, @RequestBody String newPassword) throws JsonProcessingException {

        Optional<Token> token = tokenService.findTokenByValue(tokenValue);
        if(token.isPresent()){
            Token foundedToken = token.get();
            if(isNotExpired(foundedToken)){
                User user = foundedToken.getUser();
                String password = new ObjectMapper().readTree(newPassword).get("password").asText();
                userService.updateUserPassword(user, password);
                return new ResponseEntity<>("Your password has been changed", HttpStatus.OK);
            }
            return new ResponseEntity<>("Reset link expired", HttpStatus.OK);
        }else{
           return new ResponseEntity<>("Reset link is not valid", HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isNotExpired(Token foundedToken){
        LocalDateTime expirationDate = foundedToken.getExpiryDate();
        LocalDateTime now = LocalDateTime.now();
        return expirationDate.isAfter(now);
    }
}
