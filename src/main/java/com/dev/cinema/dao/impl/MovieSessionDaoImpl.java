package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.MovieSessionDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Dao
public class MovieSessionDaoImpl extends AbstractDao<MovieSession> implements MovieSessionDao {
    private static final Logger logger = LoggerFactory.getLogger(MovieSessionDaoImpl.class);

    @Override
    public MovieSession add(MovieSession session) {
        return super.add(session, MovieSession.class);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "FROM MovieSession ms "
                    + "join fetch ms.cinemaHall "
                    + "join fetch ms.movie "
                    + "WHERE movie_id = :movieId "
                    + "AND showtime BETWEEN :startTime AND :endTime",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("startTime", date.atStartOfDay());
            query.setParameter("endTime", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Cant fetch MovieSessions for movieId={}, date={} from DB",
                    movieId, date, e);
            return Collections.emptyList();
        }
    }
}
