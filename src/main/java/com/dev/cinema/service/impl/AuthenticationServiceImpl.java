package com.dev.cinema.service.impl;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService cartService;

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
        user = userService.add(user);
        cartService.registerNewShoppingCart(user);
        return user;
    }

    private boolean isPasswordValid(User user, String password) {
        return HashUtil.hashPassword(password, user.getSalt())
                .equals(user.getPassword());
    }
}
