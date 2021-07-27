package com.gtbr.gtbrshield.service;

import com.gtbr.gtbrshield.entity.Api;
import com.gtbr.gtbrshield.entity.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
@Service
public class JwtService {

    private final String secret;

    public JwtService(@Value("${jwt.secret}") final String secret) {
        this.secret = secret;
    }

    public String generateApiToken(Api api) {
        return Jwts.builder()
                .setHeaderParam("alg", "HS512")
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date())
                .setIssuer("GTBR - Shield")
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim("api-name", api.getName())
                .compact();
    }

    public String generateUserToken(User user){
        return Jwts.builder()
                .setHeaderParam("alg", "HS512")
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date())
                .setIssuer("GTBR - Shield")
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim("email", user.getEmail())
                .compact();
    }

}
