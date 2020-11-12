package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.entity.Role;

public interface RoleDao {
    Role add(Role role);

    Role getByName(Role.RoleName roleName);
}
