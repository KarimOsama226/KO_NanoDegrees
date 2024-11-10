package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String SECRET_KEY = "MySecretKey_ProjectCICD";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public static String generateToken(String username) {
        logger.info("Generating a Token for {}",username);

        String token =  JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .sign(ALGORITHM);
        logger.info("Generating a Token for {}",token);
        return token;
    }

    public static String validateToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }
}
