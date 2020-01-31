package at.ac.fernfh.foodtracker.controller;

import at.ac.fernfh.foodtracker.exception.ResourceNotFoundException;
import at.ac.fernfh.foodtracker.model.Rating;
import at.ac.fernfh.foodtracker.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RatingController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/ratings")
    public List<Rating> getAllRatings() {
        LOG.info("Retrieving all ratings");
        return ratingRepository.findAll();
    }

    @GetMapping("/ratings/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable(value = "id") final String ratingId) throws ResourceNotFoundException {
        LOG.info("Retrieving rating by id :: " + ratingId);
        final Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found for this id :: " + ratingId));
        return ResponseEntity.ok().body(rating);
    }

    @GetMapping("/ratings/user/{id}")
    public List<Rating> getRatingByUser(@PathVariable(value = "id") final String userId) {
        LOG.info("Retrieving ratings by user :: " + userId);
        return ratingRepository.getRatingsByUser(userId);
    }

    @GetMapping("/ratings/restaurant/{id}")
    public List<Rating> getRatingByRestaurant(@PathVariable(value = "id") final String restaurantId) {
        LOG.info("Retrieving ratings by restaurant :: " + restaurantId);
        return ratingRepository.getRatingsByRestaurant(restaurantId);
    }

    @GetMapping("/ratings/avg/{id}")
    public ResponseEntity<Double> getAverageRatingByRestaurant(@PathVariable(value = "id") final String restaurantId) {
        LOG.info("Retrieving average rating of restaurant :: " + restaurantId);
        final List<Rating> ratings = ratingRepository.getRatingsByRestaurant(restaurantId);
        double avgRating = 0.0;
        for (Rating rating : ratings) {
            avgRating += rating.getRating();
        }
        avgRating /= ratings.size();
        return ResponseEntity.ok().body(avgRating);
    }

    @GetMapping("/ratings/{id}/{id2}")
    public List<Rating> getRatingByUserAndRestaurant(@PathVariable(value = "id") final String userId,
                                                     @PathVariable(value = "id2") final String restaurantId) {
        LOG.info("Retrieving ratings by user :: " + userId + " and restaurant :: " + restaurantId);
        return ratingRepository.getRatingsByUserAndRestaurant(userId, restaurantId);
    }

    @PostMapping("/ratings")
    public Rating createRating(@Valid @RequestBody final Rating rating) {
        LOG.info("Creating rating :: " + rating);
        return ratingRepository.save(rating);
    }

    @PutMapping("/ratings/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable(value = "id") final String ratingId,
                                               @Valid @RequestBody final Rating ratingDetails) throws ResourceNotFoundException {
        LOG.info("Updating rating :: " + ratingDetails);
        final Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found for this id :: " + ratingId));

        rating.setRestaurant(ratingDetails.getRestaurant());
        rating.setUser(ratingDetails.getUser());
        rating.setDate(ratingDetails.getDate());
        rating.setDish(ratingDetails.getDish());
        rating.setRating(ratingDetails.getRating());
        rating.setComment(ratingDetails.getComment());
        rating.setImages(ratingDetails.getImages());
        final Rating updatedRating = ratingRepository.save(rating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/ratings/{id}")
    public Map<String, Boolean> deleteRating(@PathVariable(value = "id") final String ratingId) throws ResourceNotFoundException {
        LOG.info("Deleting rating :: " + ratingId);
        final Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found for this id :: " + ratingId));

        ratingRepository.delete(rating);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
