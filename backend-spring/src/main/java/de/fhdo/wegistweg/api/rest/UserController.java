package de.fhdo.wegistweg.api.rest;

import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.service.PasswordEncoder;
import de.fhdo.wegistweg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get all users
    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Create a new user
    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Sign up a new user
    @PostMapping(value = "/signup", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<User> signUp(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User signedUpUser = userService.createUser(user);
        return ResponseEntity.ok(signedUpUser);
    }

    // Update an existing user
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Get a single user by ID
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
