package com.bzdgs.dms.endpoint;

import com.bzdgs.dms.resault.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: RestAuthenticationEntryPoint
 * @Description: Rest认证出入点
 * @author Chunjie He
 * @date 2017-09-01
 * @since 1.7
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException ex) throws IOException, ServletException {

		ResponseEntity<String> resp = new ResponseEntity<>();
		resp.setCode(HttpStatus.UNAUTHORIZED.value());
		resp.setMessage(ex.getMessage());

		// 设置返回错误编码 和 MediaType
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		this.mapper.writeValue(response.getWriter(), resp);
	}
}
