package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.entity.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
