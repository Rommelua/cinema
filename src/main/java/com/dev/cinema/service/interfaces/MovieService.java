package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.entity.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
