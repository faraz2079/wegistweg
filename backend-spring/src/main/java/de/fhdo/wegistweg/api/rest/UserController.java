package de.fhdo.wegistweg.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import de.fhdo.wegistweg.service.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
