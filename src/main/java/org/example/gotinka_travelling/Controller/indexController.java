package org.example.gotinka_travelling.Controller;

import org.example.gotinka_travelling.Service.GuideService;
import org.example.gotinka_travelling.Service.PackageService;
import org.example.gotinka_travelling.Service.UserService;
import org.example.gotinka_travelling.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/index")
public class indexController {
    private final UserService userService;

    @Autowired
    public indexController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        // Get current logged-in user's details
        User currentUser = userService.getCurrentUser();
        stats.put("userName", currentUser.getName());
        stats.put("userEmail", currentUser.getEmail());

        return stats;
    }
}
