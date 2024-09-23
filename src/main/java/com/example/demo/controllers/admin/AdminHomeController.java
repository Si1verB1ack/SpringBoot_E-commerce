package com.example.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "admin/index";
    }
}
