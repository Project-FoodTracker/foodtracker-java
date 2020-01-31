package at.ac.fernfh.foodtracker.controller;

import at.ac.fernfh.foodtracker.exception.ResourceNotFoundException;
import at.ac.fernfh.foodtracker.model.User;
import at.ac.fernfh.foodtracker.repository.UserRepository;
import at.ac.fernfh.foodtracker.service.UserDetailsServiceImpl;
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
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        LOG.info("Retrieving all user");
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") final String userId) throws ResourceNotFoundException {
        LOG.info("Retrieving user by id ::" + userId);
        final User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody final User user) {
        LOG.info("Creating user :: " + user);
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") final String userId, @Valid @RequestBody final User userDetails)
            throws ResourceNotFoundException {
        LOG.info("Updating user :: " + userDetails);
        final User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        final User updatedUser = userDetailsService.saveAndEncodePassword(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") final String userId) throws ResourceNotFoundException {
        LOG.info("Deleting user :: " + userId);
        final User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
