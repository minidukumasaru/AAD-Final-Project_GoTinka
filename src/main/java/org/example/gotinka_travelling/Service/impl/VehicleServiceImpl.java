package org.example.gotinka_travelling.Service.impl;

import org.example.gotinka_travelling.Service.VehicleService;
import org.example.gotinka_travelling.Repo.VehicleRepository;
import org.example.gotinka_travelling.dto.VehicleDTO;
import org.example.gotinka_travelling.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepo.findByIsDeletedFalse().stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle.setDeleted(false);  // Ensure the vehicle is not deleted by default
        vehicleRepo.save(vehicle);
    }

    @Override
    public void updateVehicle(int id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setCapacity(vehicleDTO.getCapacity());
        vehicle.setPricePerDay(vehicleDTO.getPricePerDay());
        //    vehicle.setAvailable(vehicleDTO.isAvailable());

        vehicleRepo.save(vehicle);
    }

    @Override
    public void deleteVehicle(int id) {
        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setDeleted(true);  // Soft delete
        vehicleRepo.save(vehicle);
    }
}
