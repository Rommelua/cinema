package com.dev.cinema.service.interfaces;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.entity.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
