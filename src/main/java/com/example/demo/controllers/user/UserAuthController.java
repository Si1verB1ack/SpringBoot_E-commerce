package com.example.demo.controllers.user;

import com.example.demo.models.User;
import com.example.demo.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String login(Model model) {
        model.addAttribute("page", "");
        model.addAttribute("parentPages", Set.of(""));
        model.addAttribute("newspaper", false);
        return "user/auth/login";
    }

    @GetMapping("/user/register")
    public String register(Model model) {
        model.addAttribute("page", "");
        model.addAttribute("parentPages", Set.of(""));
        model.addAttribute("newspaper", false);
        return "user/auth/register";
    }

    @PostMapping("/user/registration-process")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
//         Check if form has validation errors
        if (result.hasErrors()) {
            return "user/auth/register"; // Return back to form if errors exist
        }

        userService.registerUser(user);

        return "redirect:/login"; // Redirect to login after success
    }
}
