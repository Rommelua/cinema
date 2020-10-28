package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.User;

public interface AuthenticationService {
    User register(String email, String password);
}
