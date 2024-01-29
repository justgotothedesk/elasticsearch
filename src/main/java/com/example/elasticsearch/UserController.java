package com.example.elasticsearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final UserJPAService userJPAService;

    public UserController(UserService userService, UserJPAService userJPAService) {
        this.userService = userService;
        this.userJPAService = userJPAService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String addUser() {
        for (int i = 10000; i < 14884; i++) {
            UserJPA user = new UserJPA();
            user.setId(String.valueOf(i));
            user.setName("temp"+String.valueOf(i));
            user.setAge(i);
            userJPAService.saveUser(user);
        }
        return "redirect:/";
    }
}
