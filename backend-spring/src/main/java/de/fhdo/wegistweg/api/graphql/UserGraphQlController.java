package de.fhdo.wegistweg.api.graphql;

import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.service.UserService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;

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
}
