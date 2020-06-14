package com.bzdgs.dms.service.impl;

import com.bzdgs.dms.domain.Permission;
import com.bzdgs.dms.mapper.BaseMapper;
import com.bzdgs.dms.mapper.PermissionMapper;
import com.bzdgs.dms.query.PermissionQuery;
import com.bzdgs.dms.service.IPermissionService;
import com.bzdgs.dms.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: BeLost_
 * @Description: TODO
 * @Date: Create in 3:36 2019/12/6
 * @Version : v1.0
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    protected BaseMapper<Permission> getMapper() {
        return permissionMapper;
    }

    @Override
    public PageList<Permission> page(PermissionQuery query) {
        Long total = permissionMapper.count(query);
        List<Permission> rows = permissionMapper.selectByQuery(query);
        return new PageList<>(total,rows);
    }

    @Override
    public Set<String> findPermissionsByEmployeeId(Serializable id) {
        List<Permission> list = permissionMapper.findPermissionsByEmployeeId(id);
        Set<String> permissions = new HashSet<>();
        for (Permission p : list) {
            permissions.add(p.getUrl());
        }
        return permissions;
    }
}
