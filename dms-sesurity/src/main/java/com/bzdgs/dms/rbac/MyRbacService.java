package com.bzdgs.dms.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyRbacService {
    boolean findAuthority(HttpServletRequest request, Authentication authentication);
}
