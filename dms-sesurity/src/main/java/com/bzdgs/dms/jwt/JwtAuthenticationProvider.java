package com.bzdgs.dms.jwt;

import com.bzdgs.dms.config.JwtProperties;
import com.bzdgs.dms.model.UserContext;
import com.bzdgs.dms.model.token.AccessJwtToken;
import com.bzdgs.dms.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @ClassName: JwtAuthenticationProvider.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/15 22:25
 * @Vsersion: 1.0.0
 **/
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtAuthenticationProvider(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken token = (RawAccessJwtToken) authentication.getCredentials();
        //解析toekn
        Jws<Claims> claimsJws = token.parseClaims(this.jwtProperties.getTokenSigningKey());
        UserContext userContext = UserContext.create(claimsJws.getBody().getSubject(), new ArrayList<GrantedAuthority>());
        return new JwtAuthenticationToken(userContext.getAuthorities(),userContext);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
