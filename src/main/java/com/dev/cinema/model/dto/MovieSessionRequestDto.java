package com.dev.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull(message = "MovieId can't be null")
    private Long movieId;
    @NotNull(message = "CinemaHallId can't be null")
    private Long cinemaHallId;
    @NotNull(message = "ShowTime can't be null")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime showTime;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
