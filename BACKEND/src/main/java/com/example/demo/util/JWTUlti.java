/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.util;

import com.example.demo.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.text.ParseException;

/**
 *
 * @author PC
 */
@Component
@Slf4j
public class JWTUlti {

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;
    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(User user) {
        JWSHeader jwsHeader=new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claimsSet=new JWTClaimsSet.Builder()
                .expirationTime(new Date(new Date().getTime() + jwtExpirationMs))
                .issuer("Son")
                .subject(user.getUsername())
                .issueTime(new Date())
                .claim("scope", user.getRole())
                .build();
        Payload payload=new Payload(claimsSet.toJSONObject());
        JWSObject jWSObject=new JWSObject(jwsHeader, payload);
        try {
            jWSObject.sign(new MACSigner(jwtSecret));
        } catch (JOSEException ex) {
            log.error(ex.getMessage());
        }
        return jWSObject.serialize();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromToken(String token) throws ParseException {
        SignedJWT signedJWT=SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }
    public boolean validateJwtToken(String authToken) throws ParseException {
        SignedJWT signedJWT=SignedJWT.parse(authToken);
        Date expirationTime=signedJWT.getJWTClaimsSet().getExpirationTime();
        if(expirationTime.before(new Date())){
            return false;
        }
        try {
            boolean verified =signedJWT.verify(new MACVerifier(jwtSecret));
            return verified;
        } catch (JOSEException ex) {
            log.error("JOSEEx: {}",ex.getMessage());
        }
        return false;
    
  }
}
