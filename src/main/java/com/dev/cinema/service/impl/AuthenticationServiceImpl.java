package com.dev.cinema.service.impl;

import com.dev.cinema.model.entity.Role;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.AuthenticationService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService cartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, ShoppingCartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        user.setRoles(Set.of(Role.USER));
        userService.add(user);
        cartService.registerNewShoppingCart(user);
        return user;
    }
}
