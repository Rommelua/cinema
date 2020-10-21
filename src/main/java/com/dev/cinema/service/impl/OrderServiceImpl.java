package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService cartService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService cartService) {
        this.orderDao = orderDao;
        this.cartService = cartService;
    }

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
