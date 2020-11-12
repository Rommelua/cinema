package com.dev.cinema.service;

import com.dev.cinema.model.entity.Role;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.RoleService;
import com.dev.cinema.service.interfaces.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InjectDataService {
    private final RoleService roleService;
    private final UserService userService;

    public InjectDataService(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initRoles() {
        Role adminRole = new Role(Role.RoleName.ADMIN);
        Role userRole = new Role(Role.RoleName.USER);
        adminRole = roleService.add(adminRole);
        roleService.add(userRole);
        User admin = new User("admin", "1111");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
    }
}
