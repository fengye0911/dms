package com.bzdgs.dms.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: AjaxLoginProcessFilter.java
 * Description:
 *
 * @author luozhiyun@cloudwalk.cn
 * @date 2020/6/9
 */
public class AjaxLoginProcessFilter extends AbstractAuthenticationProcessingFilter {

    protected AjaxLoginProcessFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
