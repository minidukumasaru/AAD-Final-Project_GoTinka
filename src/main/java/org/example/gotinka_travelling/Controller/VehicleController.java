package org.example.gotinka_travelling.Controller;

import jakarta.validation.Valid;
import org.example.gotinka_travelling.Service.VehicleService;
import org.example.gotinka_travelling.dto.VehicleDTO;
import org.example.gotinka_travelling.dto.ResponseDTO;
import org.example.gotinka_travelling.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Save Vehicle
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDTO saveVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        vehicleService.saveVehicle(vehicleDTO);
        return new ResponseDTO(201, "Vehicle saved successfully", null);
    }

    // Get All Vehicles
    @GetMapping("/getAll")
    public ResponseDTO getAllVehicles() {
        return new ResponseDTO(200, "Get all Vehicles", vehicleService.getAllVehicles());
    }
}
