package com.bzdgs.dms.controller;

import com.bzdgs.dms.domain.Menu;
import com.bzdgs.dms.query.MenuQuery;
import com.bzdgs.dms.service.IMenuService;
import com.bzdgs.dms.util.AjaxResult;
import com.bzdgs.dms.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: BeLost_
 * @Description: TODO
 * @Date: Create in 2:58 2019/12/6
 * @Version : v1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @PostMapping("/list")
    public AjaxResult list(@RequestBody MenuQuery menuQuery){
        PageList<Menu> pageList = menuService.page(menuQuery);
            return AjaxResult.me().setResobj(pageList);
    }
    @PostMapping("/page")
    public AjaxResult menuall(){
        List<Menu> menuByUserId = menuService.getMenuByUserId(1L);
        return AjaxResult.me().setResobj(menuByUserId);
    }


}
