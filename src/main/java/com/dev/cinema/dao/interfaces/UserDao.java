package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.entity.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    User get(Long id);

    Optional<User> findByEmail(String email);
}
