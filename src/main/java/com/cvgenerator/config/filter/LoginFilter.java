package com.cvgenerator.config.filter;

import com.cvgenerator.config.security.AuthenticationService;
import com.cvgenerator.domain.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Component
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationService authenticationService;

    @Autowired
    public LoginFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        UsernamePasswordAuthenticationToken token =  new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword(),
                Collections.emptyList());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult){

        User user = (User)authResult.getPrincipal();
        authenticationService.createToken(user, response);
    }
}
