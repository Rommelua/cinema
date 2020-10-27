package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.entity.Order;
import com.dev.cinema.model.entity.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
