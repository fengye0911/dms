package com.bzdgs.dms.config;

import com.bzdgs.dms.CustomCorsFilter;
import com.bzdgs.dms.endpoint.RestAuthenticationEntryPoint;
import com.bzdgs.dms.ajax.AjaxLoginProcessFilter;
import com.bzdgs.dms.ajax.AjaxAuthenticationProvider;
import com.bzdgs.dms.ajax.AjaxAuthenticationSuccessHandler;
import com.bzdgs.dms.ajax.AjaxAwareAuthenticationFailureHandler;
import com.bzdgs.dms.jwt.JwtAuthenticationProcessFilter;
import com.bzdgs.dms.jwt.JwtAuthenticationProvider;
import com.bzdgs.dms.jwt.SkipPathRequestMatcher;
import com.bzdgs.dms.jwt.extractor.TokenExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SecurityConfig.java
 * Description:
 *
 * @author luozhiyun@cloudwalk.cn
 * @date 2020/6/9
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * JWT TOKEN 头部授权参数KEY
     */
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";

    /**
     * 身份认证入口
     */
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";

    /**
     * 刷新token
     */
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";

    /**
     * 拦截路径，这里是将所有的路径进行拦截
     */
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenExtractor tokenExtractor;

    protected AjaxLoginProcessFilter buildAjaxLoginProcessFilter(){
        AjaxLoginProcessFilter ajaxLoginProcessFilter = new AjaxLoginProcessFilter("/login",
                objectMapper,successHandler,failureHandler);
        ajaxLoginProcessFilter.setAuthenticationManager(this.authenticationManager);
        return ajaxLoginProcessFilter;
    }

    protected JwtAuthenticationProcessFilter buildJwtAuthenticationProcessFilter(){
        List<String> pathsToSkip = new ArrayList<>();
        pathsToSkip.add(TOKEN_REFRESH_ENTRY_POINT);
        pathsToSkip.add(FORM_BASED_LOGIN_ENTRY_POINT);

        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtAuthenticationProcessFilter jwtAuthenticationProcessFilter = new JwtAuthenticationProcessFilter(failureHandler,tokenExtractor,matcher);
        jwtAuthenticationProcessFilter.setAuthenticationManager(this.authenticationManager);
        return jwtAuthenticationProcessFilter;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(this.ajaxAuthenticationProvider);
        auth.authenticationProvider(this.jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers("/**")
//                .access("@MyRbacService.findAuthority(request,authentication)")
                .authenticated()

                .and()
                .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildAjaxLoginProcessFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtAuthenticationProcessFilter(),UsernamePasswordAuthenticationFilter.class);
    }
}
