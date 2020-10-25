package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
