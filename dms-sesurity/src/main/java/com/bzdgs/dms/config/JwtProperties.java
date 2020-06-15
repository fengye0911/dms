package com.bzdgs.dms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: JwtConfig.java
 * @Description： jwt属性配置类
 * @Author: yun
 * @Date: 2020/6/15 21:34
 * @Vsersion: 1.0.0
 **/
@Configuration
@ConfigurationProperties("dms.security.jwt")
public class JwtProperties {

    private static final int TOKEN_EXPIRATION_TIME=30;

    private static final int REFRESH_TOKEN_EXP_TIME=60;

    private static final String ISSUER="www.bzdgs.com";

    private static final String TOKEN_SIGN_KEY="SDkyMTAyOENK";

    private Integer tokenExpirationTime=TOKEN_EXPIRATION_TIME;

    private Integer refreshTokenExpTime=REFRESH_TOKEN_EXP_TIME;

    private String issuer=ISSUER;

    private String tokenSigningKey=TOKEN_SIGN_KEY;

    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public Integer getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }
}
