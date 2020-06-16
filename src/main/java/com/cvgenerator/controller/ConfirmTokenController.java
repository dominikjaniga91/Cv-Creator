package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Token;
import com.cvgenerator.domain.entity.User;
import com.cvgenerator.exceptions.TokenExpiredException;
import com.cvgenerator.repository.UserRepository;
import com.cvgenerator.service.implementation.TokenServiceImpl;
import com.cvgenerator.utils.service.implementation.MailServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Optional;

@Api(tags = "Confirmation token controller")
@RestController
@RequestMapping("/api")
public class ConfirmTokenController {

    private final TokenServiceImpl tokenService;
    private final UserRepository userRepository;
    private final MailServiceImpl mailService;

    @Autowired
    public ConfirmTokenController(TokenServiceImpl tokenService,
                                  UserRepository userRepository,
                                  MailServiceImpl mailService) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @ApiOperation(value = "Check confirmation link. More precisely method check if " +
                            "token value exist in database and its expiry time. " +
                            "When token exist and is not expired then user account is activate otherwise s" +
                            "end error in response")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Your account is active"),
            @ApiResponse(code=400, message = "Confirmation link doesn't exist"),
            @ApiResponse(code=401, message = "Confirmation link expired")
    })
    @GetMapping("/token")
    public ResponseEntity<?> checkConfirmationEmailToken(@ApiParam(value = "Value of token which was send with confirmation link")
                                                         @RequestParam("value") String tokenValue){

        Token token = tokenService.findTokenByValue(tokenValue);
        User user = token.getUser();
        user.setActive(true);
        userRepository.save(user);
        mailService.sendWelcomeEmail(user);
        return new ResponseEntity<>("Your account is active", HttpStatus.OK);
    }
}
