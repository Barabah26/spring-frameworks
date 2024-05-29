package com.example.springsecurity.controller;

import com.example.springsecurity.service.UserDetailsServiceImpl;
import com.example.springsecurity.service.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserDetailsServiceImpl userDetailsService;
    private final UserDtoMapper userDtoMapper;

    // For basic auth
    @GetMapping("/")
    public String basicAuth(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        log.info("User name: {}", name);

        model.addAttribute("employees", new String[]{"ddff"});
        printSecurityUserName();

        return "redirect:/dashboard";
    }

    private String printSecurityUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println(name);

        return name;
    }

    // For user details auth
    @GetMapping("/login")
    public String userDetails(Model model) {
        model.addAttribute("employees", new String[]{"ddff"});

        return "/login";
    }

    @GetMapping("/registration")
    public String userRegistrationPage(Model model) {
        return "registration";
    }

    @PostMapping("/create")
    public String createNewUser(@RequestParam String login, @RequestParam String password) {
        userDetailsService.createUser(login, password);

        return "redirect:/login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "/index";
    }

    @GetMapping("/dashboard")
    public String dashboardGet() {
        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
