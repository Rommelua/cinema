package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.interfaces.AuthenticationService;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.interfaces.MovieSessionService;
import com.dev.cinema.service.interfaces.OrderService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws AuthenticationException {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MovieService movieService = context.getBean(MovieService.class);
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        movieService.add(fastAndFurious);

        Movie titanic = new Movie();
        titanic.setTitle("Titanic");
        movieService.add(titanic);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Green hall");
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);

        MovieSession sessionOne = new MovieSession();
        sessionOne.setCinemaHall(cinemaHall);
        sessionOne.setMovie(fastAndFurious);
        sessionOne.setShowTime(LocalDateTime.now());
        MovieSessionService sessionService = context.getBean(MovieSessionService.class);
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
        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        authenticationService.register("bob@gmail.com", "1111");
        authenticationService.register("alisa@gmail.com", "1111");
        User bob = authenticationService.login("bob@gmail.com", "1111");
        ShoppingCartService cartService = context.getBean(ShoppingCartService.class);
        logger.info("bob's cart: " + cartService.getByUser(bob));
        cartService.addSession(sessionOne, bob);
        cartService.addSession(sessionOne, bob);
        cartService.addSession(sessionTwo, bob);
        ShoppingCart cart = cartService.getByUser(bob);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(cart);
        List<Order> orders = orderService.getOrderHistory(cart.getUser());
        orders.forEach(order -> logger.info(order.toString()));
    }
}
