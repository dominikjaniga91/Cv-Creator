package com.cvgenerator.config.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value(("${jwt.header}"))
    private String header;

    @Value(("${jwt.additionalHeader}"))
    private String additionalHeader;


}
