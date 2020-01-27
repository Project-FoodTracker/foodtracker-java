package at.ac.fernfh.foodtracker.repository;

import at.ac.fernfh.foodtracker.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, Long> {

    List<Rating> getRatingsByUser(final Long userId);

    List<Rating> getRatingsByRestaurant(final Long restaurantId);
}
