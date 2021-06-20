package com.ololo.Restcrud313.controllers;

import com.ololo.Restcrud313.model.User;
import com.ololo.Restcrud313.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String viewUsersList(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.findAllUsers());
        model.addAttribute("allRoles", userService.findAllRoles());
        model.addAttribute("newUser", new User());
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "admin";
    }

    @GetMapping("/user")
    public String showUserPage(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "user";
    }


    @GetMapping("/")
    public String login() {
        return "redirect:/login";
    }

}
