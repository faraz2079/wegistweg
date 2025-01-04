package de.fhdo.wegistweg.api.thymeleaf;

import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("th/login")
public class ThymeleafLoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    // Handles the login form submission
    @PostMapping
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model) {
        try {

            User user = userService.loginUser(email, password);

            model.addAttribute("success", "Login successful! Welcome, " + user.getUsername() + ".");

            return "login";

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}





