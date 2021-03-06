package com.bzdgs.dms.service;

import com.bzdgs.dms.domain.Menu;
import com.bzdgs.dms.query.MenuQuery;
import com.bzdgs.dms.util.PageList;

import java.util.List;

public interface IMenuService extends IBaseService<Menu> {
    //根据当前登陆用户拿到他对应的菜单
    List<Menu> findByLoginUser();

    List<Menu> getMenuByUserId(Long id);

    PageList<Menu> page(MenuQuery menuQuery);
}
