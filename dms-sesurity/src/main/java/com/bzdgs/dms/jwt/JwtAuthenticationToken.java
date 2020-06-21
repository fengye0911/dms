package com.bzdgs.dms.jwt;

import com.bzdgs.dms.model.UserContext;
import com.bzdgs.dms.model.token.RawAccessJwtToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @ClassName: JwtAuthenticationToken.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/15 22:49
 * @Vsersion: 1.0.0
 **/
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private RawAccessJwtToken rawAccessJwtToken;

    private UserContext userContext;


    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities,UserContext userContext) {
        super(authorities);
        this.userContext = userContext;
        this.eraseCredentials();
        this.setAuthenticated(true);

    }

    public JwtAuthenticationToken(RawAccessJwtToken unsafeToken){
        super(null);
        this.rawAccessJwtToken = unsafeToken;
        this.setAuthenticated(false);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return this.rawAccessJwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userContext;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessJwtToken = null;
    }
}
