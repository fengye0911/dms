package com.bzdgs.dms.model.token;

import io.jsonwebtoken.Claims;

/**
 * @ClassName: AccessJwtToken.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/14 22:25
 * @Vsersion: 1.0.0
 **/
public class AccessJwtToken implements JwtToken {
    private final String rawToken;

    private Claims claims;

    public AccessJwtToken(String rawToken,Claims claims){
        this.rawToken = rawToken;
        this.claims = claims;
    }
    @Override
    public String getToekn() {
        return this.rawToken;
    }
}
