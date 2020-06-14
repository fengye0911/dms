package com.bzdgs.dms.ajax;

import com.bzdgs.dms.model.UserContext;
import com.bzdgs.dms.model.UserTokenRequest;
import com.bzdgs.dms.service.IMenuService;
import com.bzdgs.dms.service.IPermissionService;
import com.bzdgs.dms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IPermissionService permissionService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserTokenRequest userTokenRequest = (UserTokenRequest)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        //创建用户上下文对象
//        User user = userService.getByUsername(userTokenRequest.getUsername());
//        Set<String> permissions = permissionService.findPermissionsByEmployeeId(user.getId());
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admin");
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        list.add(grantedAuthority);
        UserContext userContext = UserContext.create(userTokenRequest.getUsername(),list);

        return new UsernamePasswordAuthenticationToken(userContext,null,userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
