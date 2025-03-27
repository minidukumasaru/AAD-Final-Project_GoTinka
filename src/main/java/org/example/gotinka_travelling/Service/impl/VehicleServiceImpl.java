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

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepository.save(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDTO>>() {}.getType());
    }
}
