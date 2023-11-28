package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigateController {

    @GetMapping("/")
    public String getHomePage() {
        return "homePage";
    }

    @GetMapping("/register")
    public String register() {
        return "registerUser";
    }

    @GetMapping("/signIn")
    public String login() {
        return "login";
    }

}
