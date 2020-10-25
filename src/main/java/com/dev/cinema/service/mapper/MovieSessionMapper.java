package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.entity.CinemaHall;
import com.dev.cinema.model.entity.Movie;
import com.dev.cinema.model.entity.MovieSession;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    public MovieSessionResponseDto mapToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setId(movieSession.getId());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setMovieTitle(movieSession.getMovie().getTitle());
        dto.setShowTime(movieSession.getShowTime());
        return dto;
    }

    public MovieSession mapToEntity(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(new Movie(dto.getMovieId()));
        movieSession.setCinemaHall(new CinemaHall(dto.getCinemaHallId()));
        movieSession.setShowTime(dto.getShowTime());
        return movieSession;
    }
}
