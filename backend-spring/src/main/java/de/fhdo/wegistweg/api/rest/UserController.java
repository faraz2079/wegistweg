package de.fhdo.wegistweg.api.rest;

import de.fhdo.wegistweg.service.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.service.PasswordEncoder;
import de.fhdo.wegistweg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Get all users
    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        return userService.signUpUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        userService.resetPassword(email, newPassword);
    }

    // Get a single user by ID
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
