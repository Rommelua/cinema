package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.entity.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    MovieSession add(MovieSession session);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession get(Long id);
}
