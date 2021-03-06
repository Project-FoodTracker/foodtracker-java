package at.ac.fernfh.foodtracker;

import at.ac.fernfh.foodtracker.model.*;
import at.ac.fernfh.foodtracker.repository.RatingRepository;
import at.ac.fernfh.foodtracker.repository.RestaurantRepository;
import at.ac.fernfh.foodtracker.repository.UserRepository;
import at.ac.fernfh.foodtracker.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        testDataSetup();
    }

    private void testDataSetup() {
        clearDatabase();

        final User u1 = new User("Mustermann", "Max", "user1", "pwd");
        final User u2 = new User("Maier", "Thomas", "user2", "pwd");
        final User u3 = new User("Zwettl", "Peter", "user3", "pwd");
        userDetailsService.saveAndEncodePassword(u1);
        userDetailsService.saveAndEncodePassword(u2);
        userDetailsService.saveAndEncodePassword(u3);

        final Map<String, OpeningHours> openingHours = new LinkedHashMap<>();
        openingHours.put("Montag-Freitag", new OpeningHours(new Date(2020, 1, 1, 9, 30), new Date(2020, 1, 1, 20, 15)));
        openingHours.put("Samstag", new OpeningHours(new Date(2020, 1, 1, 10, 10), new Date(2020, 1, 1, 18, 30)));
        openingHours.put("Sonntag", null);

        final Restaurant res1 = new Restaurant("McDonalds", "McStreet", "1", "8010", "Österreich", 47.0631116f, 15.4341873f,
                                               "I'm loving it", openingHours, PriceClass.ONE, null);
        final Restaurant res2 = new Restaurant("PeterPan", "Teststraße", "2", "8010", "Österreich", 47.03f, 15.41f, "", openingHours,
                                               PriceClass.TWO, null);
        final Restaurant res3 = new Restaurant("StarFood", "Starstreet", "99", "8010", "Österreich", 47.09f, 15.46f, "Food with class",
                                               openingHours, PriceClass.FOUR, null);
        restaurantRepository.save(res1);
        restaurantRepository.save(res2);
        restaurantRepository.save(res3);

        final List<Image> images = new ArrayList<>();
        images.add(new Image("MC-Donalds.jpg", "/McDonalds/"));
        res1.setImages(images);
        final List<Image> images2 = new ArrayList<>();
        images2.add(new Image("PeterPan.jpg", "/PeterPan/"));
        res2.setImages(images2);
        final List<Image> images3 = new ArrayList<>();
        images3.add(new Image("StarFood.jpg", "/StarFood/"));
        res3.setImages(images3);

        restaurantRepository.save(res1);
        restaurantRepository.save(res2);
        restaurantRepository.save(res3);

        final Rating r1 = new Rating(u1.getId(), res2.getId(), "Pizza", "was great", new Date(), 4.1, images);
        final Rating r2 = new Rating(u2.getId(), res2.getId(), "Pasta", "best pasta ever!", new Date(), 3.8, images);
        final Rating r3 = new Rating(u3.getId(), res1.getId(), "Cheeseburger", "was fine", new Date(), 3.5, images);
        ratingRepository.save(r1);
        ratingRepository.save(r2);
        ratingRepository.save(r3);
    }

    private void clearDatabase() {
        userRepository.deleteAll();
        restaurantRepository.deleteAll();
        ratingRepository.deleteAll();
    }
}
