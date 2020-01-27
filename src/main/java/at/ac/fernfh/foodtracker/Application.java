package at.ac.fernfh.foodtracker;

import at.ac.fernfh.foodtracker.model.*;
import at.ac.fernfh.foodtracker.repository.RatingRepository;
import at.ac.fernfh.foodtracker.repository.RestaurantRepository;
import at.ac.fernfh.foodtracker.repository.UserRepository;
import at.ac.fernfh.foodtracker.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        testDataSetup();
    }

    private void testDataSetup() {
        clearDatabase();

        final User u1 = new User("Mustermann", "Max", "user1", "pwd", "", "");
        final User u2 = new User("Maier", "Thomas", "user2", "pwd", "", "");
        final User u3 = new User("Zwettl", "Peter", "user3", "pwd", "", "");
        u1.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userRepository.save(u1);
        u2.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userRepository.save(u2);
        u3.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userRepository.save(u3);

        final Map<String, OpeningHours> openingHours = new LinkedHashMap<>();
        openingHours.put("Montag", new OpeningHours(new Date(), new Date()));
        openingHours.put("Mittwoch", new OpeningHours(new Date(), new Date()));
        openingHours.put("Sonntag", null);
        final List<Image> images = new ArrayList<>();
        images.add(new Image("image1.png", "./images"));
        final Restaurant res1 = new Restaurant("McDonalds", "McStreet", "1", "3100", "Österreich", 1f, 1f, "I'm loving it", openingHours,
                                               PriceClass.ONE, images);
        final Restaurant res2 = new Restaurant("PeterPan", "Teststraße", "2", "3100", "Österreich", 5f, 8f, "", openingHours,
                                               PriceClass.TWO, images);
        final Restaurant res3 = new Restaurant("StarFood", "Starstreet", "99", "4100", "Österreich", 9f, 9f, "Food with class",
                                               openingHours, PriceClass.FOUR, images);
        res1.setId(sequenceGeneratorService.generateSequence(Restaurant.SEQUENCE_NAME));
        restaurantRepository.save(res1);
        res2.setId(sequenceGeneratorService.generateSequence(Restaurant.SEQUENCE_NAME));
        restaurantRepository.save(res2);
        res3.setId(sequenceGeneratorService.generateSequence(Restaurant.SEQUENCE_NAME));
        restaurantRepository.save(res3);

        final Rating r1 = new Rating(1L, 2L, "Pizza", "was great", new Date(), 4.1, images);
        final Rating r2 = new Rating(2L, 2L, "Pasta", "", new Date(), 3.8, images);
        final Rating r3 = new Rating(3L, 1L, "Cheesburger", "", new Date(), 3.5, images);
        r1.setId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
        ratingRepository.save(r1);
        r2.setId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
        ratingRepository.save(r2);
        r3.setId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
        ratingRepository.save(r3);
    }

    private void clearDatabase() {
        userRepository.deleteAll();
        sequenceGeneratorService.resetSequence(User.SEQUENCE_NAME);
        restaurantRepository.deleteAll();
        sequenceGeneratorService.resetSequence(Restaurant.SEQUENCE_NAME);
        ratingRepository.deleteAll();
        sequenceGeneratorService.resetSequence(Rating.SEQUENCE_NAME);
    }
}
