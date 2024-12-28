package de.fhdo.wegistweg.api.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("th/login")
public class ThymeleafLoginController {

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    // Handles the login form submission
    @PostMapping
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model) {
        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Email and Password cannot be empty.");
            return "login";
        }

        if ("faraz@mail.com".equals(email) && "1234".equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login";
        }
    }
}





