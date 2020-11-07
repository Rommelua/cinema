package com.dev.cinema.service.impl;

import com.dev.cinema.model.entity.Role;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.AuthenticationService;
import com.dev.cinema.service.interfaces.RoleService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService cartService;
    private final RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService cartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.cartService = cartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        Role userRole = roleService.getByName(Role.RoleName.USER);
        user.setRoles(Set.of(userRole));
        userService.add(user);
        cartService.registerNewShoppingCart(user);
        return user;
    }
}
