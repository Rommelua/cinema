package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.ShoppingCartDao;
import com.dev.cinema.model.entity.ShoppingCart;
import com.dev.cinema.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends AbstractDao<ShoppingCart> implements ShoppingCartDao {
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        return super.add(shoppingCart, ShoppingCart.class);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ShoppingCart sc "
                                       + "left join fetch sc.tickets "
                                       + "where sc.user = :user ", ShoppingCart.class)
                    .setParameter("user", user)
                    .uniqueResult();
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        super.update(shoppingCart, ShoppingCart.class);
    }
}
