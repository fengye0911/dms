package com.bzdgs.dms.provider;

import com.bzdgs.dms.model.UserContext;
import com.bzdgs.dms.model.UserTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
        UserTokenRequest userTokenRequest = (UserTokenRequest)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        //创建用户上下文对象
        UserContext userContext = UserContext.create(userTokenRequest.getUserName(),new ArrayList<GrantedAuthority>());
        return new UsernamePasswordAuthenticationToken(userContext,null,userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
