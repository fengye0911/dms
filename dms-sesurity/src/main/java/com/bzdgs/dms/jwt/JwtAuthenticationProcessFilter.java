package com.bzdgs.dms.jwt;

import com.bzdgs.dms.config.SecurityConfig;
import com.bzdgs.dms.jwt.extractor.TokenExtractor;
import com.bzdgs.dms.model.token.RawAccessJwtToken;
import com.bzdgs.dms.wrapper.CustomHeaderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName: JwtAuthenticationProcessFilter.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/15 22:58
 * @Vsersion: 1.0.0
 **/
public class JwtAuthenticationProcessFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * token提取器
     */
    private final TokenExtractor tokenExtractor;

    /**
     * 权限失败处理器
     */
    private final AuthenticationFailureHandler failureHandler;

    /**
     * 构造方法
     * @param failureHandler
     * @param tokenExtractor
     * @param matcher
     */
    @Autowired
    public JwtAuthenticationProcessFilter(final AuthenticationFailureHandler failureHandler,
                                          final TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        /**
         * 优先从请求头中获取token
         */
        String token = request.getHeader(SecurityConfig.JWT_TOKEN_HEADER_PARAM);
        try {
            /**
             * 请求头中无token，从参数中获取token
             */
            if (StringUtils.isEmpty(token)) {
                final String newToken = request.getParameter(SecurityConfig.JWT_TOKEN_HEADER_PARAM);
                /**
                 * 请求中存在token,将token添加到请求头中
                 */
                if (!StringUtils.isEmpty(newToken)) {
                    req = new CustomHeaderWrapper(request, new HashMap<String, String>(1) {{
                        put(SecurityConfig.JWT_TOKEN_HEADER_PARAM, newToken);
                    }});
                }
            }
            super.doFilter(req, res, chain);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(SecurityConfig.JWT_TOKEN_HEADER_PARAM);
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
