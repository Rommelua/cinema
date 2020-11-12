package com.dev.cinema.security;

import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService service;

    @Autowired
    public UserDetailsServiceImpl(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        try {
            user = service.findByEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with email {" + email
                                                + "} has not been found!");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(role -> role.getRoleName().toString())
                        .toArray(String[]::new))
                .build();
    }
}
