package com.bzdgs.dms.jwt.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @ClassName: JwtHeaderTokenExtractor
 * @Description: JWT 头部token提取器
 * @author Chunjie He
 * @date 2017-09-01
 * @since 1.7
 */
@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
	
	/**
	 * token前缀
	 */
    public final static String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
    	
        if (StringUtils.isEmpty(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
