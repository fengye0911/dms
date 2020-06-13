package com.bzdgs.dms.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;

public class UserContext {
    private  String username;
    private final List<GrantedAuthority> authorities ;

    public UserContext(String username,List<GrantedAuthority> authorities){
        this.username = username;
        this.authorities = authorities;

    }

    public static UserContext create(String username,List<GrantedAuthority> authorities){
        return new UserContext(username,authorities);
    }
    public String getUsername(){
        return this.username;
    }
    public List<GrantedAuthority> getAuthorities(){
        return this.authorities;
    }

}
