package com.bzdgs.dms.rbac;

import com.bzdgs.dms.domain.User;
import com.bzdgs.dms.model.UserContext;
import com.bzdgs.dms.rbac.MyRbacService;
import com.bzdgs.dms.service.IPermissionService;
import com.bzdgs.dms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @ClassName: MyRbacServiceImpl.java
 * @Description： TODO
 * @Author: yun
 * @Date: 2020/6/14 20:36
 * @Vsersion: 1.0.0
 **/
//@Component("MyRbacService")
public class MyRbacServiceImpl implements MyRbacService {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;
    private AntPathMatcher antPathMatcher=new AntPathMatcher();
    @Override
    public boolean findAuthority(HttpServletRequest request, Authentication authentication) {
        boolean authority=false;
        Cookie[] cookies = request.getCookies();
        Cookie cookie = cookies[0];
        String value = cookie.getValue();
        if (authentication.getPrincipal() instanceof UserContext){
            String username = ((UserContext) authentication.getPrincipal()).getUsername();
            User user = userService.getByUsername(username);
            Set<String> permissions = permissionService.findPermissionsByEmployeeId(user.getId());

            for (String url:permissions){
                if (antPathMatcher.match(url,request.getRequestURI())){
                    authority=true;
                    break;
                }
            }
            return authority;
        }
        return authority;
    }
}
