package com.dev.cinema.service.mapper;

import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.entity.Order;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    @Autowired
    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto(order.getId(), order.getOrderTime());
        List<TicketResponseDto> tickets = order.getTickets().stream()
                .map(ticketMapper::mapToDto)
                .collect(Collectors.toList());
        dto.setTickets(tickets);
        return dto;
    }
}
