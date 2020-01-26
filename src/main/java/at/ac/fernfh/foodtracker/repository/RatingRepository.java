package at.ac.fernfh.foodtracker.repository;

import at.ac.fernfh.foodtracker.model.Rating;
import at.ac.fernfh.foodtracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends MongoRepository<Rating, Long> {
}