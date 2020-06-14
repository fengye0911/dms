package com.bzdgs.dms.ajax;


import com.bzdgs.dms.enums.CommonEnumClass;
import com.bzdgs.dms.GeneratorResult;
import com.bzdgs.dms.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: AjaxAwareAuthenticationFailureHandler
 * @Description: Ajax 授权失败处理对象
 * @author Chunjie He
 * @date 2017-09-01
 * @since 1.7
 */
@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

	/**
	 * Mapper
	 */
	@Autowired
	private final ObjectMapper objectMapper;

	/**
	 * 构造方法
	 * @param objectMapper
	 */
    @Autowired
    public AjaxAwareAuthenticationFailureHandler(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        // 设置返回错误编码 和 MediaType
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        //解决bug 前端没有传token的时候提示 登录是报错 未知鉴权异常
        ResponseEntity<String> resp = GeneratorResult.genResult(HttpStatus.UNAUTHORIZED.value(), "Authentication failed");
        if (e instanceof UsernameNotFoundException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getKey(), CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getValue());
        } else if (e instanceof BadCredentialsException) {
            response.setStatus(HttpStatus.OK.value());
            resp = GeneratorResult.genResult(CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getKey(), CommonEnumClass.CommonInterfaceEnum.INVALID_ACCOUNT_PASSWORD.getValue());
        }
        try {
         resp.setMessage(messageSource.getMessage(resp.getFullCode(), null,resp.getMessage(), LocaleContextHolder.getLocale()));
        }catch (Exception e1){

        }
        this.objectMapper.writeValue(response.getWriter(), resp);
    }
}
