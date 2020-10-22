package com.dev.cinema.service.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User unmapUser(UserRequestDto dto) {
        return new User(dto.getEmail(), dto.getPassword());
    }
}
