package com.dev.cinema.model.dto;

import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<TicketResponseDto> tickets;
    private String orderDate;

    public OrderResponseDto(Long id, String orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    public OrderResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
