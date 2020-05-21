package com.cvgenerator.config.security;

import com.cvgenerator.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

@Service
@NoArgsConstructor
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

    public Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(jwtConfig.getHeader());

        if(token != null && token.startsWith("Bearer")){

            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfig.getSecret())
                                .parseClaimsJws(token.substring(8))
                                .getBody();

            String username = claims.getSubject();
            String role = "ROLE_" + claims.get("role").toString();

            if(username != null){
                return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
            }
        }

        return null;
    }

}
