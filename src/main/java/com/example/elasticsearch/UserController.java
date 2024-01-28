package com.example.elasticsearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String addUser() {
        for (int i = 5; i < 40000; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName("temp"+String.valueOf(i));
            user.setAge(i);
            userService.saveUser(user);
        }
        return "redirect:/";
    }
}
