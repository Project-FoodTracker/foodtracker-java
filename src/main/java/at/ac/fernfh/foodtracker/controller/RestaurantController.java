package at.ac.fernfh.foodtracker.controller;

import at.ac.fernfh.foodtracker.exception.ResourceNotFoundException;
import at.ac.fernfh.foodtracker.model.Restaurant;
import at.ac.fernfh.foodtracker.repository.RestaurantRepository;
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
public class RestaurantController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        LOG.info("Retrieving all restaurants");
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value = "id") final String restaurantId)
            throws ResourceNotFoundException {
        LOG.info("Retrieving restaurant by id :: " + restaurantId);
        final Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));
        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping("/restaurants/postalCode/{id}")
    public List<Restaurant> getRestaurantsById(@PathVariable(value = "id") final String postalCode) {
        LOG.info("Retrieving restaurants by postal code :: " + postalCode);
        return restaurantRepository.getRestaurantsByPostalCode(postalCode);
    }

    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@Valid @RequestBody final Restaurant restaurant) {
        LOG.info("Creating restaurant :: " + restaurant);
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value = "id") final String restaurantId,
                                                       @Valid @RequestBody final Restaurant restaurantDetails)
            throws ResourceNotFoundException {
        LOG.info("Updating restaurant :: " + restaurantDetails);
        final Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));

        restaurant.setCountry(restaurantDetails.getCountry());
        restaurant.setImages(restaurantDetails.getImages());
        restaurant.setLatitude(restaurantDetails.getLatitude());
        restaurant.setLongitude(restaurantDetails.getLongitude());
        restaurant.setName(restaurantDetails.getName());
        restaurant.setNumber(restaurantDetails.getNumber());
        restaurant.setOpeningHours(restaurantDetails.getOpeningHours());
        restaurant.setPostalCode(restaurantDetails.getPostalCode());
        restaurant.setStreet(restaurantDetails.getStreet());
        restaurant.setPriceClass(restaurantDetails.getPriceClass());
        restaurant.setDescription(restaurantDetails.getDescription());
        final Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/restaurants/{id}")
    public Map<String, Boolean> deleteRestaurant(@PathVariable(value = "id") final String restaurantId) throws ResourceNotFoundException {
        LOG.info("Deleting restaurant :: " + restaurantId);
        final Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));

        restaurantRepository.delete(restaurant);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
