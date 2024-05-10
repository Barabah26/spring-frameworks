package com.danit.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private List<User> users = Arrays.asList(
            new User("Jack", "M", 25),
            new User("Ann", "F", 23),
            new User("Steve", "M", 30),
            new User("Jim", "M", 15),
            new User("Melony", "F", 28)
    );


    @GetMapping("/users")
    public String findAllUsers(Model model) {
        model.addAttribute("users", users);

        return "users"; //view
    }


    @PostMapping("/user")
    public String findUserByName(@RequestParam(name = "name") String name, Model model) {
        User activeUser = users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElseThrow();

        model.addAttribute("user", activeUser);

        return "user"; //view

    }

    @ExceptionHandler({Exception.class})
    public void handleException(Exception ex) {
        System.err.println(ex);

    }
}
