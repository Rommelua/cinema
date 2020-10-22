package com.dev.cinema.service.mapper;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    public MovieSessionResponseDto mapMovieSession(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setId(movieSession.getId());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setMovieTitle(movieSession.getMovie().getTitle());
        dto.setShowTime(movieSession.getShowTime().format(DateTimeFormatter.ISO_DATE_TIME));
        return dto;
    }

    public MovieSession unmapMovieSession(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(new Movie(dto.getMovieId()));
        movieSession.setCinemaHall(new CinemaHall(dto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(dto.getShowTime(),
                DateTimeFormatter.ISO_DATE_TIME));
        return movieSession;
    }
}
