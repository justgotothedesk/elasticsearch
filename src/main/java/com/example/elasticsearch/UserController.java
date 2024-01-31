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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String addUser() {
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam String query,
            @RequestParam String searchType,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        if (searchType.equals("name") && !query.isEmpty()) {
            Page<User> userPage = userService.findByNameWildcard(query, pageable);
            List<User> users = userPage.getContent();

            model.addAttribute("users", users);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", userPage.getTotalPages());
            model.addAttribute("query", query);
            model.addAttribute("searchType", searchType);
        } else if (searchType.equals("age") && !query.isEmpty()) {
            Page<User> userPage = userService.findByAge(Integer.parseInt(query), pageable);
            List<User> users = userPage.getContent();

            model.addAttribute("users", users);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", userPage.getTotalPages());
            model.addAttribute("query", query);
            model.addAttribute("searchType", searchType);
        }

        return "home";
    }
}
