package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    User get(Long id);

    Optional<User> findByEmail(String email);
}
