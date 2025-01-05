package de.fhdo.wegistweg.api.graphql;

import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserGraphQlController {

    private final UserService userService;

    public UserGraphQlController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @MutationMapping("createUser")
    public User createUser(@Argument String username, @Argument String email, @Argument String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // In a real app, make sure to hash the password
        return userService.createUser(user);
    }

    // Mutation to update an existing user
    @MutationMapping("updateUser")
    public User updateUser(@Argument Long id, @Argument String username, @Argument String email) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            existingUser.setUsername(username);
            existingUser.setEmail(email);
            return userService.updateUser(existingUser.getId(), existingUser);
        }
        throw new RuntimeException("User not found");
    }

    // Mutation to delete a user
    @MutationMapping("deleteUser")
    public Boolean deleteUser(@Argument Long id) {
        userService.deleteUser(id);
        return true;
    }
}
