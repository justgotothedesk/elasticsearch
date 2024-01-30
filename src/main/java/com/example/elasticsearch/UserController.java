package com.example.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
//        for (int i = 10000; i < 14884; i++) {
//            UserJPA user = new UserJPA();
//            user.setId(String.valueOf(i));
//            user.setName("temp"+String.valueOf(i));
//            user.setAge(i);
//            userJPAService.saveUser(user);
//        }
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, @RequestParam String searchType, Model model) {
        if (searchType.equals("name")) {
            List<User> users = userService.findByNameWildcard(query);
            model.addAttribute("users", users);
        } else if (searchType.equals("age")) {
            List<User> users = userService.findByAgeWildcard(query);
            model.addAttribute("users", users);
        }
        return "home";
    }

//    @GetMapping("/search")
//    public String search(@RequestParam String query, @RequestParam String searchType,
//                         @RequestParam(defaultValue = "0") int page, Model model) {
//        int pageSize = 10;
//
//        List<User> users;
//        Pageable pageable = PageRequest.of(page, pageSize);
//
//        if (searchType.equals("name")) {
//            Page<User> userPage = userService.findByNameWildcard(query, pageable);
//            users = userPage.getContent();
//
//            model.addAttribute("users", users);
//            model.addAttribute("currentPage", page);
//            model.addAttribute("totalPages", userPage.getTotalPages());
//        } else if (searchType.equals("age")) {
//            Page<User> userPage = userService.findByAgeWildcard(Integer.parseInt(query), pageable);
//            users = userPage.getContent();
//
//            model.addAttribute("users", users);
//            model.addAttribute("currentPage", page);
//            model.addAttribute("totalPages", userPage.getTotalPages());
//        }
//
//        return "home";
//    }
}
