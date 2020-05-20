package com.cvgenerator.config.security;

import com.cvgenerator.domain.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

@Service
public class AuthenticationService {

    private JwtConfig jwtConfig;

    @Autowired
    public AuthenticationService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public void createToken(User user, HttpServletResponse response){

        long expiration = System.currentTimeMillis() + jwtConfig.getExpiration();

        String token =  Jwts
                        .builder()
                        .claim("role", user.getRole())
                        .setSubject(user.getUsername())
                        .setExpiration(new Date(expiration))
                        .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                        .compact();

        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
        response.addHeader(jwtConfig.getAdditionalHeader(), jwtConfig.getHeader());
    }

}
