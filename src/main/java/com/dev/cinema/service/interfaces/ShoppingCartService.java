package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.MovieSession;
import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;

public interface ShoppingCartService {
    /**
     * This method is responsible to add a Ticket to the ShoppingCart
     * @param movieSession Contains the information required for a ticket
     * @param user - user who wan't to buy a ticket for a specific MovieSession
     */
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
