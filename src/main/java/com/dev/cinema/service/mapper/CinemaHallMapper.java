package com.dev.cinema.service.mapper;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {
    public CinemaHallResponseDto mapToDto(CinemaHall cinemaHall) {
        return new CinemaHallResponseDto(cinemaHall.getId(),
                cinemaHall.getCapacity(), cinemaHall.getDescription());
    }

    public CinemaHall mapToEntity(CinemaHallRequestDto dto) {
        return new CinemaHall(dto.getCapacity(), dto.getDescription());
    }
}
