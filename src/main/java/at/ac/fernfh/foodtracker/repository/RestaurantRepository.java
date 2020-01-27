package at.ac.fernfh.foodtracker.repository;

import at.ac.fernfh.foodtracker.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Long> {

    List<Restaurant> getRestaurantsByPostalCode(final String postalCode);
}
