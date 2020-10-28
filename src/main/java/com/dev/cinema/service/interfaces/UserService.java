package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.User;
import java.util.Optional;
import org.springframework.security.core.Authentication;

public interface UserService {
    User add(User user);

    User get(Long id);

    Optional<User> findByEmail(String email);

    User getUser(Authentication authentication);
}
