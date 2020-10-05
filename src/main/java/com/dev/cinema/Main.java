package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Movie fast = new Movie();
        fast.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) Injector.getInstance(MovieService.class);
        movieService.add(fast);

        Movie titanic = new Movie();
        titanic.setTitle("Titanic");
        movieService.add(titanic);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Green hall");
        CinemaHallService cinemaHallService
                = (CinemaHallService) Injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(fast);
        session.setShowTime(LocalDateTime.now());
        MovieSessionService sessionService
                = (MovieSessionService) Injector.getInstance(MovieSessionService.class);
        sessionService.add(session);

        session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(titanic);
        session.setShowTime(LocalDateTime.now());
        sessionService.add(session);

        session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(fast);
        session.setShowTime(LocalDateTime.now().minusDays(2));
        sessionService.add(session);

        sessionService.findAvailableSessions(1L, LocalDate.now())
                .forEach(System.out::println);
    }
}
