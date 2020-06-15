package com.bzdgs.dms.model.token;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * @ClassName: RawAccessJwtToken.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/15 22:36
 * @Vsersion: 1.0.0
 **/
public class RawAccessJwtToken implements JwtToken {

    private String token;

    public RawAccessJwtToken(String token){
        this.token = token;
    }


    public Jws<Claims> parseClaims(String signingKey){
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new BadCredentialsException("Invalid JWT token: ", e);
        }
    }
    @Override
    public String getToekn() {
        return null;
    }
}
