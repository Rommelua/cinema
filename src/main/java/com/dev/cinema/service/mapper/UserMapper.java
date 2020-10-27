package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapToDto(User user) {
        return new UserResponseDto(user.getId(), user.getEmail(), user.getPassword());
    }
}
