package com.dev.cinema.dao.impl;

import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AbstractDao<T> {
    protected T add(T instance, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(instance);
            transaction.commit();
            return instance;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + clazz.getSimpleName() + " entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    protected List<T> getAll(Class<T> clazz) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery("from " + clazz.getSimpleName(), clazz);
            return query.getResultList();
        }
    }

    protected void update(T instance, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(instance);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update "
                    + clazz.getSimpleName() + " entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
