package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.service.implementation.TokenServiceImpl;
import com.cvgenerator.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class ConfirmTokenController {

    private final TokenServiceImpl tokenService;
    private final UserServiceImpl userService;

    @Autowired
    public ConfirmTokenController(TokenServiceImpl tokenService,
                                  UserServiceImpl userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @GetMapping("/token")
    public ResponseEntity<?> checkConfirmationEmailToken(@RequestParam("value") String tokenValue){

        Optional<Token> token = tokenService.findTokenByValue(tokenValue);
        if (token.isPresent()){

            Token foundedToken = token.get();
            if(isNotExpired(foundedToken)){
                User user = foundedToken.getUser();
                user.setActive(true);
                userService.updateUser(user);
                return new ResponseEntity<>("Your account is active", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Confirmation link expired", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Confirmation link doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isNotExpired(Token foundedToken){
        LocalDateTime expirationDate = foundedToken.getExpiryDate();
        LocalDateTime now = LocalDateTime.now();
        return expirationDate.isAfter(now);

    }
}
