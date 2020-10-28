package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.UserDao;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User add(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getUser(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        return findByEmail(details.getUsername()).get();
    }
}
