package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService cartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order(new ArrayList<>(shoppingCart.getTickets()),
                LocalDateTime.now(), shoppingCart.getUser());
        cartService.clear(shoppingCart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getByUser(user);
    }
}
