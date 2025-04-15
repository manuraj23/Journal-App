package com.JournelApp.JournelApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    @GetMapping("/journalApp/dashboard")
    public String dashboardPage(HttpServletRequest request) {
        // Fetch token from Authorization header
        String token = request.getHeader("Authorization");

        // Check if token exists and is not empty
        if (token == null || token.isEmpty()) {
            return "redirect:/login";  // Redirect to login if no token found
        }

        // Add your token validation logic here (for example, JWT validation)
        boolean isValid = validateToken(token);

        if (!isValid) {
            return "redirect:/login";  // Redirect if token is invalid
        }

        return "dashboard";  // Return dashboard view if token is valid
    }

    // Dummy token validation function
    private boolean validateToken(String token) {
        // You can implement JWT validation here or any custom logic
        // Example: return JwtUtils.isTokenValid(token);
        return true; // This is a placeholder
    }
}
