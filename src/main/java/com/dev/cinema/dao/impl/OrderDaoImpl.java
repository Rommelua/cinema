package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

@Dao
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    @Override
    public Order add(Order order) {
        return super.add(order, Order.class);
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Order o "
                                       + "join fetch o.tickets "
                                       + "where o.user = :user ", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        }
    }
}
