package org.example.gotinka_travelling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private int vehicleId;
    private String registrationNumber;
    private String type; // CAR, BUS, VAN
    private Integer capacity;
    private Boolean available;
    private Double pricePerDay;

}
