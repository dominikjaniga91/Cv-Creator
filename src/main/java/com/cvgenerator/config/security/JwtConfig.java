package com.cvgenerator.config.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
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
