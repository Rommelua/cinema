package com.dev.cinema.controller;

import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import com.dev.cinema.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService cartService;
    private final UserService userService;
    private final OrderMapper mapper;

    @Autowired
    public OrderController(OrderService orderService, ShoppingCartService cartService,
                           UserService userService, OrderMapper mapper) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {
        User user = userService.get(userId);
        ShoppingCart shoppingCart = cartService.getByUser(user);
        orderService.completeOrder(shoppingCart);
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(@RequestParam Long userId) {
        return orderService.getOrderHistory(new User(userId)).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
}