package com.example.demo.controllers.admin;

import com.example.demo.models.User;
import com.example.demo.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminAuthController {


    private final UserService userService;

    public AdminAuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/login")
    public String login() {
        return "admin/auth/login";
    }

    @GetMapping("/admin/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "admin/auth/register";
    }

    @PostMapping("/admin/registration-process")
    public String registerProcess(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
           return "redirect:/admin/register";
        }
        userService.registerUser(user);

        return "redirect:/admin/login";
    }
}
