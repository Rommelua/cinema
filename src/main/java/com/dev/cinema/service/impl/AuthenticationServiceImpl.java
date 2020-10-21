package com.dev.cinema.service.impl;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.AuthenticationService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;
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
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && isPasswordValid(user.get(), password)) {
            return user.get();
        }
        throw new AuthenticationException("Login or password is incorrect");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        userService.add(user);
        cartService.registerNewShoppingCart(user);
        return user;
    }

    private boolean isPasswordValid(User user, String password) {
        return HashUtil.hashPassword(password, user.getSalt())
                .equals(user.getPassword());
    }
}
