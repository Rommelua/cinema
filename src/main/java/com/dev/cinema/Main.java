package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final MovieService movieService
            = (MovieService) Injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService
            = (CinemaHallService) Injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService sessionService
            = (MovieSessionService) Injector.getInstance(MovieSessionService.class);
    private static final UserService userService
            = (UserService) Injector.getInstance(UserService.class);
    private static final AuthenticationService authenticationService
            = (AuthenticationService) Injector.getInstance(AuthenticationService.class);

    public static void main(String[] args) {
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        movieService.add(fastAndFurious);

        Movie titanic = new Movie();
        titanic.setTitle("Titanic");
        movieService.add(titanic);
        movieService.getAll().forEach(System.out::println);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Green hall");

        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(fastAndFurious);
        session.setShowTime(LocalDateTime.now());
        sessionService.add(session);

        session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(titanic);
        session.setShowTime(LocalDateTime.now());
        sessionService.add(session);

        session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(fastAndFurious);
        session.setShowTime(LocalDateTime.now().minusDays(2));
        sessionService.add(session);
        sessionService.findAvailableSessions(1L, LocalDate.now())
                .forEach(System.out::println);

        authenticationService.register("bob@gmail.com", "1111");
        authenticationService.register("alisa@gmail.com", "1111");
        User bob = authenticationService.login("bob@gmail.com", "1111");
        User alisa = authenticationService.login("alisa@gmail.com", "1111");
    }
}
