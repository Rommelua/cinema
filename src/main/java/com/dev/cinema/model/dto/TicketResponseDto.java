package com.dev.cinema.model.dto;

public class TicketResponseDto {
    private Long id;
    private MovieSessionResponseDto movieSessionDto;

    public TicketResponseDto(Long id) {
        this.id = id;
    }

    public TicketResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieSessionResponseDto getMovieSessionDto() {
        return movieSessionDto;
    }

    public void setMovieSessionDto(MovieSessionResponseDto movieSessionDto) {
        this.movieSessionDto = movieSessionDto;
    }
}
