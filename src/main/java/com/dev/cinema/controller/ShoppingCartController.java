package com.dev.cinema.controller;

import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.model.entity.MovieSession;
import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;
import com.dev.cinema.service.interfaces.MovieSessionService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final MovieSessionService sessionService;
    private final ShoppingCartMapper cartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService cartService,
                                  MovieSessionService sessionService,
                                  ShoppingCartMapper cartMapper) {
        this.cartService = cartService;
        this.sessionService = sessionService;
        this.cartMapper = cartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSessionToCart(@RequestParam Long sessionId,
                                      @RequestParam Long userId) {
        MovieSession movieSession = sessionService.get(sessionId);
        User user = new User(userId);
        cartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        ShoppingCart cart = cartService.getByUser(new User(userId));
        return cartMapper.mapToDto(cart);
    }
}
