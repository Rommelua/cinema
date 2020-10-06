package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl extends AbstractDao<MovieSession> implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession session) {
        return super.add(session, MovieSession.class);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.atTime(LocalTime.MAX);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "FROM MovieSession WHERE movie_id = :movieId "
                                 + "AND showtime BETWEEN :startTime AND :endTime",
                            MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);
            return query.getResultList();
        }
    }
}
