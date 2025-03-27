package org.example.gotinka_travelling.Service;

import org.example.gotinka_travelling.dto.VehicleDTO;
import org.example.gotinka_travelling.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);  // Save a new vehicle
    List<VehicleDTO> getAllVehicles();  // Get all vehicles

}
