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
    public String addUser() {
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setAge(20);
        userService.saveUser(user);

        return "redirect:/users";
    }
}
