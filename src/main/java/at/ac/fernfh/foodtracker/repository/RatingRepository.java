package at.ac.fernfh.foodtracker.repository;

import at.ac.fernfh.foodtracker.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> getRatingsByUser(final String userId);

    List<Rating> getRatingsByRestaurant(final String restaurantId);

    List<Rating> getRatingsByUserAndRestaurant(final String userId, final String restaurantId);
}
