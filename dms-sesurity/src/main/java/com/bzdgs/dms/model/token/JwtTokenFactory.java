package com.bzdgs.dms.model.token;

import com.bzdgs.dms.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.time.LocalDateTime;

/**
 * @ClassName: JwtTokenFactory.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/14 22:29
 * @Vsersion: 1.0.0
 **/
public class JwtTokenFactory {

    private AccessJwtToken createAccessToken(UserContext ctx){
        Claims claims = Jwts.claims().setSubject(ctx.getUsername());
        LocalDateTime currentTime = LocalDateTime.now();

        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);

        return new AccessJwtToken(jwtBuilder.toString(),claims);

    }
}
