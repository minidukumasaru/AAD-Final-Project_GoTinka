package org.example.gotinka_travelling.Controller;

import org.example.gotinka_travelling.Service.GuideService;
import org.example.gotinka_travelling.Service.PackageService;
import org.example.gotinka_travelling.Service.PackageService;
import org.example.gotinka_travelling.Service.UserService;
import org.example.gotinka_travelling.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {

    private final PackageService packagesService;
    private final UserService userService;
    private final GuideService guideService;

    @Autowired
    public DashboardController(PackageService packagesService,UserService userService, GuideService guideService) {
        this.packagesService = packagesService;
        this.userService = userService;
        this.guideService = guideService;
    }



    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        // Get active packages count
        Long activePackagesCount = packagesService.getActivePackagesCount();
        stats.put("activePackagesCount", activePackagesCount);

        // Get user count with role "USER"
        Long userCount = userService.getUserCountByRoleUser();
        stats.put("userCount", userCount);

        // Get available guides count
        Long availableGuidesCount = guideService.getAvailableGuidesCount();
        stats.put("availableGuidesCount", availableGuidesCount);

        // Get current logged-in user's details
        User currentUser = userService.getCurrentUser();
        stats.put("userName", currentUser.getName());
        stats.put("userEmail", currentUser.getEmail());

        return stats;
    }
}