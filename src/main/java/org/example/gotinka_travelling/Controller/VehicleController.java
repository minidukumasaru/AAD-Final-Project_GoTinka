package org.example.gotinka_travelling.Controller;

import jakarta.validation.Valid;
import org.example.gotinka_travelling.Service.UserService;
import org.example.gotinka_travelling.Service.VehicleService;
import org.example.gotinka_travelling.Util.JwtUtil;
import org.example.gotinka_travelling.Util.ResponseUtil;
import org.example.gotinka_travelling.dto.VehicleDTO;
import org.example.gotinka_travelling.dto.ResponseDTO;
import org.example.gotinka_travelling.entity.User;
import org.example.gotinka_travelling.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {
    private final UserService userService;

    //constructor injection
    public VehicleController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/getAll")
    public ResponseUtil getAllVehicles() {
        List<VehicleDTO> vehicleDTOList = vehicleService.getAllVehicles();
        return new ResponseUtil(200, "Vehicles Retrieved Successfully", vehicleDTOList);
    }

    @PostMapping("/save")
    public ResponseUtil saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        vehicleService.saveVehicle(vehicleDTO);
        return new ResponseUtil(201, "Vehicle Saved Successfully", null);
    }

    @PutMapping("/update/{id}")
    public ResponseUtil updateVehicle(@PathVariable int id, @RequestBody VehicleDTO vehicleDTO) {
        vehicleService.updateVehicle(id, vehicleDTO);
        return new ResponseUtil(200, "Vehicle Updated Successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        return new ResponseUtil(200, "Vehicle Deleted Successfully", null);
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
