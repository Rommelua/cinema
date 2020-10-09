package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDateTime;

public class Main {
    private static final MovieService movieService
            = (MovieService) Injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService
            = (CinemaHallService) Injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService sessionService
            = (MovieSessionService) Injector.getInstance(MovieSessionService.class);
    private static final AuthenticationService authenticationService
            = (AuthenticationService) Injector.getInstance(AuthenticationService.class);
    private static final ShoppingCartService cartService
            = (ShoppingCartService) Injector.getInstance(ShoppingCartService.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        movieService.add(fastAndFurious);

        Movie titanic = new Movie();
        titanic.setTitle("Titanic");
        movieService.add(titanic);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Green hall");
        cinemaHallService.add(cinemaHall);

        MovieSession sessionOne = new MovieSession();
        sessionOne.setCinemaHall(cinemaHall);
        sessionOne.setMovie(fastAndFurious);
        sessionOne.setShowTime(LocalDateTime.now());
        sessionService.add(sessionOne);

        MovieSession sessionTwo = new MovieSession();
        sessionTwo.setCinemaHall(cinemaHall);
        sessionTwo.setMovie(titanic);
        sessionTwo.setShowTime(LocalDateTime.now().plusHours(1));
        sessionService.add(sessionTwo);

        sessionOne = new MovieSession();
        sessionOne.setCinemaHall(cinemaHall);
        sessionOne.setMovie(fastAndFurious);
        sessionOne.setShowTime(LocalDateTime.now().minusHours(2));
        sessionService.add(sessionOne);

        authenticationService.register("bob@gmail.com", "1111");
        authenticationService.register("alisa@gmail.com", "1111");
        User bob = authenticationService.login("bob@gmail.com", "1111");
        User alisa = authenticationService.login("alisa@gmail.com", "1111");
        System.out.println(cartService.getByUser(bob));
        cartService.addSession(sessionOne, bob);
        cartService.addSession(sessionOne, bob);
        cartService.addSession(sessionTwo, bob);
        ShoppingCart cart = cartService.getByUser(bob);
        System.out.println(cart);
        cartService.clear(cart);
        System.out.println(cart);
    }
}
