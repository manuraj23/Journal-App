package com.JournelApp.JournelApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/journalApp")
public class PageController {

    // This method will handle GET request for the /signup URL and render the signup page
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";  // This refers to signup.html in templates folder
    }

    // This method will handle GET request for the /login URL and render the login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // This refers to login.html in templates folder
    }

//    // This method will handle GET request for the /dashboard URL and render the dashboard page
//    @GetMapping("/dashboard")
//    public String dashboardPage() {
//        return "dashboard";  // This refers to dashboard.html in templates folder
//    }

}

