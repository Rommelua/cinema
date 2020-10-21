package com.dev.cinema.dao.impl;

import com.dev.cinema.exception.DataProcessingException;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    protected final SessionFactory sessionFactory;

    @Autowired
    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected T add(T instance, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
        try (Session session = sessionFactory.openSession()) {
            Query<T> query = session.createQuery("from " + clazz.getSimpleName(), clazz);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Cant fetch all {} from DB", clazz.getName(), e);
            return Collections.emptyList();
        }
    }

    protected void update(T instance, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
