package com.bzdgs.dms.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * ClassName: AjaxAuthenticationProvider.java
 * Description:
 *
 * @author luozhiyun@cloudwalk.cn
 * @date 2020/6/9
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
