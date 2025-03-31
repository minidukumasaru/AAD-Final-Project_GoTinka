package org.example.gotinka_travelling.Controller;

import jakarta.validation.Valid;
import org.example.gotinka_travelling.Service.VehicleService;
import org.example.gotinka_travelling.Util.ResponseUtil;
import org.example.gotinka_travelling.dto.VehicleDTO;
import org.example.gotinka_travelling.dto.ResponseDTO;
import org.example.gotinka_travelling.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {

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
}
