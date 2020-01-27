package at.ac.fernfh.foodtracker.controller;

import at.ac.fernfh.foodtracker.exception.ResourceNotFoundException;
import at.ac.fernfh.foodtracker.model.Rating;
import at.ac.fernfh.foodtracker.repository.RatingRepository;
import at.ac.fernfh.foodtracker.service.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RatingController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/ratings")
    public List<Rating> getAllRatings() {
        LOG.info("Retrieving all ratings");
        return ratingRepository.findAll();
    }

    @GetMapping("/ratings/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable(value = "id") final Long ratingId) throws ResourceNotFoundException {
        LOG.info("Retrieving rating by id :: " + ratingId);
        final Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found for this id :: " + ratingId));
        return ResponseEntity.ok().body(rating);
    }

    @GetMapping("/ratings/user/{id}")
    public List<Rating> getRatingByUser(@PathVariable(value = "id") final Long userId) {
        LOG.info("Retrieving ratings by user :: " + userId);
        return ratingRepository.getRatingsByUser(userId);
    }

    @GetMapping("/ratings/restaurant/{id}")
    public List<Rating> getRatingByRestaurant(@PathVariable(value = "id") final Long restaurantId) {
        LOG.info("Retrieving ratings by user :: " + restaurantId);
        return ratingRepository.getRatingsByRestaurant(restaurantId);
    }

    @PostMapping("/ratings")
    public Rating createRating(@Valid @RequestBody final Rating rating) {
        LOG.info("Creating rating :: " + rating);
        rating.setId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
        return ratingRepository.save(rating);
    }

    @PutMapping("/ratings/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable(value = "id") final Long ratingId,
                                               @Valid @RequestBody final Rating ratingDetails) throws ResourceNotFoundException {
        LOG.info("Updating rating :: " + ratingDetails);
        final Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found for this id :: " + ratingId));

        rating.setComment(ratingDetails.getComment());
        final Rating updatedRating = ratingRepository.save(rating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/ratings/{id}")
    public Map<String, Boolean> deleteRating(@PathVariable(value = "id") final Long ratingId) throws ResourceNotFoundException {
        LOG.info("Deleting rating :: " + ratingId);
        final Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found for this id :: " + ratingId));

        ratingRepository.delete(rating);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
