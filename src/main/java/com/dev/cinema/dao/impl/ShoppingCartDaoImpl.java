package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.ShoppingCartDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;

@Dao
public class ShoppingCartDaoImpl extends AbstractDao<ShoppingCart> implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        return super.add(shoppingCart, ShoppingCart.class);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
