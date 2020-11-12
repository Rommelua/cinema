package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.RoleDao;
import com.dev.cinema.model.entity.Role;
import com.dev.cinema.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getByName(Role.RoleName roleName) {
        return roleDao.getByName(roleName);
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }
}
