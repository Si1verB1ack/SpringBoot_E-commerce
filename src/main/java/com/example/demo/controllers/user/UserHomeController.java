    package com.example.demo.controllers.user;

    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;

    import java.util.Set;

    @Controller
    public class UserHomeController {
        @GetMapping({"", "/"})
        public String redirectToHome() {
            return "redirect:/home";
        }

        @GetMapping("/home")
        public String home(Model model) {
            model.addAttribute("parentPages", Set.of("home"));
            model.addAttribute("page", "home");
            model.addAttribute("newspaper", true);
            return "user/index";
        }

        @GetMapping("/user/feature")
        public String feature(Model model) {
            model.addAttribute("parentPages", Set.of("home"));
            model.addAttribute("page", "feature");
            model.addAttribute("newspaper", true);
            return "user/feature";
        }

        @GetMapping("/user/shop")
        public String shop(Model model) {
            model.addAttribute("parentPages", Set.of("home"));
            model.addAttribute("page", "shop");
            model.addAttribute("newspaper", true);
            return "user/shop";
        }

        @GetMapping("/user/page-not-found")
        public String pageNotFound(Model model) {
            model.addAttribute("page", "404");
            model.addAttribute("parentPages", Set.of("pages"));
            model.addAttribute("newspaper", false);
            return "error/user-404";
        }


    }
