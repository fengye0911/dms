package com.bzdgs.dms.ajax;

import com.bzdgs.dms.resault.GeneratorResult;
import com.bzdgs.dms.resault.ResponseEntity;
import com.bzdgs.dms.domain.Menu;
import com.bzdgs.dms.domain.User;
import com.bzdgs.dms.model.UserContext;
import com.bzdgs.dms.model.token.JwtTokenFactory;
import com.bzdgs.dms.service.IMenuService;
import com.bzdgs.dms.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AjaxAuthenticationSuccessHandler.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/14 11:40
 * @Vsersion: 1.0.0
 **/
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    private final JwtTokenFactory tokenFactory;

    private final ObjectMapper mapper;

    @Autowired
    public AjaxAuthenticationSuccessHandler(final JwtTokenFactory tokenFactory,
                                            final ObjectMapper mapper){
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseEntity<Map<String,Object>> resp = GeneratorResult.genSuccessResult();
        UserContext principal = (UserContext)authentication.getPrincipal();
        HashMap<String, Object> tokenMap = new HashMap<>();
        User user = userService.getByUsername(principal.getUsername());


        // 查询 对应的菜单
        List<Menu> menus = menuService.getMenuByUserId(user.getId());
        tokenMap.put("userId",user.getId());
        tokenMap.put("menus",menus);
        tokenMap.put("sessionId",request.getSession().getId());
        tokenMap.put("token",tokenFactory.createAccessToken(principal).getToken());
        tokenMap.put("refreshToken",tokenFactory.createRefreshToken(principal).getToken());
        resp.setData(tokenMap);
        try {
            resp.setMessage(messageSource.getMessage(resp.getFullCode(),null,resp.getMessage(), LocaleContextHolder.getLocale()));
        }catch (Exception e){

        }
        this.mapper.writeValue(response.getWriter(),resp);
    }
}
