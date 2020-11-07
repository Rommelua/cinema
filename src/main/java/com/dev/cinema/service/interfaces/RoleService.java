package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.Role;

public interface RoleService {
    Role add(Role role);

    Role getByName(Role.RoleName roleName);
}
