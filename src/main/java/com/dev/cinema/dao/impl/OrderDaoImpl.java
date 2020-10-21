package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.OrderDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Order add(Order order) {
        return super.add(order, Order.class);
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select distinct o from Order o "
                                       + "join fetch o.tickets "
                                       + "where o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Cant fetch User {} from DB", user, e);
            return Collections.emptyList();
        }
    }
}
