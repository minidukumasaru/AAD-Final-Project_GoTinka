package org.example.gotinka_travelling.Controller;

import org.example.gotinka_travelling.Service.PackageService;
import org.example.gotinka_travelling.Service.UserService;
import org.example.gotinka_travelling.Util.JwtUtil;
import org.example.gotinka_travelling.Util.ResponseUtil;
import org.example.gotinka_travelling.dto.PackageDTO;
import org.example.gotinka_travelling.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/packages")
public class PackageController {
    private final UserService userService;

    //constructor injection
    public PackageController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private PackageService packageService;

    @PostMapping("/save")
    public ResponseUtil savePackage(@RequestBody PackageDTO packageDTO) {
        packageService.savePackage(packageDTO);
        return new ResponseUtil(201,"Package Created Successfully",null);
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllPackages() {
        List<PackageDTO> packageDTOList = packageService.getAllPackages();
        return new ResponseUtil(200, "Packages Retrieved Successfully", packageDTOList);
    }

    @GetMapping("get/{id}")
    public ResponseUtil getPackageById(@PathVariable int id) {
        PackageDTO packageDTO = packageService.getPackageById(id);
        return new ResponseUtil(200, "Package Retrieved Successfully", packageDTO);
    }

    @GetMapping("/getNames")
    public ResponseUtil getPackageNames() {
        return new ResponseUtil(200, "Package Names Retrieved Successfully",
                packageService.getPackageNames());
    }

    @GetMapping("/getPackageId/{name}")
    public ResponseUtil getBundleByName(@PathVariable String name) {
        Integer packageId = packageService.getPackageIdByNames(name);
        return new ResponseUtil(200, "Bundle found",packageId);
    }

    @PutMapping("/update/{id}")
    public ResponseUtil updatePackage(@PathVariable int id, @RequestBody PackageDTO packageDTO) {
        packageService.updatePackage(id, packageDTO);
        return new ResponseUtil(200, "Package Updated Successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deletePackage(@PathVariable int id) {
        packageService.deletePackage(id);
        return new ResponseUtil(200, "Package Deleted Successfully", null);
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
