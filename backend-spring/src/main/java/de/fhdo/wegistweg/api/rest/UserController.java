package de.fhdo.wegistweg.api.rest;

import de.fhdo.wegistweg.dto.ErrorResponseDto;
import de.fhdo.wegistweg.dto.LoginRequestDto;
import de.fhdo.wegistweg.dto.LoginResponseDto;
import de.fhdo.wegistweg.service.PasswordEncoder;
import org.springframework.web.ErrorResponse;
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
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        return userService.signUpUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            User authenticatedUser = userService.loginUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            return ResponseEntity.ok(new LoginResponseDto(authenticatedUser.getId(), authenticatedUser.getUsername(), "Login successful"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(new ErrorResponseDto("Login failed", ex.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        userService.resetPassword(email, newPassword);
    }

    // Get a single user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
