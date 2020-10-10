package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Movie;
import java.util.List;

@Dao
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        return super.add(movie, Movie.class);
    }

    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }
}
