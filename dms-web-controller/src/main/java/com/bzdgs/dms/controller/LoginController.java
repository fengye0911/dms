package com.bzdgs.dms.controller;

import com.bzdgs.dms.domain.Menu;
import com.bzdgs.dms.domain.User;
import com.bzdgs.dms.service.IMenuService;
import com.bzdgs.dms.service.IUserService;
import com.bzdgs.dms.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description desc
 * @Author lq
 * @Date new Date()
 * @Version v1.0
 **/

@RestController
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

//    @PostMapping("/login")
//    public AjaxResult login(@RequestBody User loginuser, HttpServletRequest request){
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(loginuser.getUsername(),loginuser.getPassword());
//        try {
//            // 登录
//            subject.login(token);
//            //  登录之后 从subject中获取用户信息 放入session中
//            User user = (User) subject.getPrincipal();
//            user.setPassword(null); // 密码置空 保证安全性
//            UserContext.INSTANCE.setUser(user); // session.setAttribute
//
//            //放入 响应消息中  员工信息  sessionID 保持前后一致
//            Map<String,Object> map = new HashMap<>();
//            map.put("user",user);
//            map.put("sessionId",request.getSession().getId());
//
//            // 查询 对应的菜单
//            List<Menu> menus = menuService.getMenuByUserId(user.getId());
//            map.put("menus",menus);
//
//            return AjaxResult.me().setMsg("登录成功").setResobj(map);
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            return AjaxResult.me().setSuccess(false).setMsg("登录失败");
//        }
//    }





}
