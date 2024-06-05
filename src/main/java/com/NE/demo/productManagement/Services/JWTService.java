package com.NE.demo.productManagement.Services;

import com.NE.demo.productManagement.Repositories.CustomerRepository;
import com.NE.demo.productManagement.Models.Customer;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algoritmkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;
    private static final String EMAIL_KEY = "EMAIL";

    @PostConstruct
    public void postContruct(){
        algorithm = Algorithm.HMAC256(algoritmkey);
    }

    public String generateJWT(Customer user){
        return JWT.create()
                .withClaim(EMAIL_KEY,user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() *(1000* expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getEmail(String token){
        return JWT.decode(token).getClaim(EMAIL_KEY).asString();
    }




}
