package com.bzdgs.dms.filter;

import com.bzdgs.dms.model.UserTokenRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

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
    @Autowired
    private ObjectMapper objectMapper;

    protected AjaxLoginProcessFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UserTokenRequest userTokenRequest = objectMapper.readValue(request.getReader(), UserTokenRequest.class);
        if (StringUtils.isEmpty(userTokenRequest.getUserName()) || StringUtils.isEmpty(userTokenRequest.getPassword())){
            throw new AuthenticationServiceException("username or pasword is not provided");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userTokenRequest,userTokenRequest.getPassword());
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
