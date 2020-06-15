package com.bzdgs.dms.model.token;

import com.bzdgs.dms.config.JwtProperties;
import com.bzdgs.dms.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: JwtTokenFactory.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/14 22:29
 * @Vsersion: 1.0.0
 **/
public class JwtTokenFactory {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtTokenFactory(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    /**
     * 创建token
     * @param ctx
     * @return
     */
    private AccessJwtToken createAccessToken(UserContext ctx){
        Claims claims = Jwts.claims().setSubject(ctx.getUsername());
        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder().setClaims(claims)
                .setIssuer(this.jwtProperties.getIssuer())
                .setExpiration(Date.from(currentTime.plusMinutes(this.jwtProperties.getTokenExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.ES512,this.jwtProperties.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token,claims);
    }


    public JwtToken createRefreshToken(UserContext ctx){
        LocalDateTime currentTime = LocalDateTime.now();
        Claims claims = Jwts.claims().setSubject(ctx.getUsername());

        String refreshToken = Jwts.builder().setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuer(this.jwtProperties.getIssuer())
                .setExpiration(Date.from(currentTime.plusMinutes(this.jwtProperties.getRefreshTokenExpTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.ES512,this.jwtProperties.getTokenSigningKey())
                .compact();
        return new AccessJwtToken(refreshToken,claims);
    }

    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }
}
