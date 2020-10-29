package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User findByEmail(String email);
}
