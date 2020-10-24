package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    private final MovieSessionMapper sessionMapper;

    @Autowired
    public TicketMapper(MovieSessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    public TicketResponseDto mapToDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto(ticket.getId());
        dto.setMovieSessionDto(sessionMapper.mapToDto(ticket.getMovieSession()));
        return dto;
    }
}
