package com.example.elasticsearch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;
    private final UserJPAService userJPAService;

    public UserApiController(UserService userService, UserJPAService userJPAService) {
        this.userService = userService;
        this.userJPAService = userJPAService;
    }

//    @GetMapping("/findelastic")
//    public List<User> findUser(@RequestParam String name) {
//        List<User> users = userService.findByNameWildcard(name);
//
//        return users;
//    }

    @GetMapping("/findrdbms")
    public List<UserJPA> findJPAUser(@RequestParam String name) {
        List<UserJPA> users = userJPAService.findByNameContaining(name);

        return users;
    }
}
