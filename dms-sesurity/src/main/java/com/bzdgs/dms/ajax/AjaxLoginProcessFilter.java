package com.bzdgs.dms.ajax;

import com.bzdgs.dms.model.UserTokenRequest;
import com.bzdgs.dms.ajax.AjaxAuthenticationSuccessHandler;
import com.bzdgs.dms.ajax.AjaxAwareAuthenticationFailureHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
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

    /**
     * Mapper
     */
    private final ObjectMapper objectMapper;

    private final AjaxAuthenticationSuccessHandler successHandler;

    private final AjaxAwareAuthenticationFailureHandler failureHandler;

    public AjaxLoginProcessFilter(String defaultFilterProcessesUrl,
                                  final ObjectMapper objectMapper,
                                  final AjaxAuthenticationSuccessHandler successHandler,
                                  final AjaxAwareAuthenticationFailureHandler failureHandler) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UserTokenRequest userTokenRequest = objectMapper.readValue(request.getReader(), UserTokenRequest.class);
        if (StringUtils.isEmpty(userTokenRequest.getUsername()) || StringUtils.isEmpty(userTokenRequest.getPassword())){
            throw new AuthenticationServiceException("username or pasword is not provided");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userTokenRequest,userTokenRequest.getPassword());
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
