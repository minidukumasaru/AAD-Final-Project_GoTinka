package org.example.gotinka_travelling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;

    @Column(name = "registration_number")
    private String registrationNumber;  // Ensure column name is correctly mapped

    private String type;  // CAR, BUS, VAN
    private Integer capacity;
    private Boolean available;
    private Double pricePerDay;
    private boolean isDeleted = false;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<VehicleBooking> vehicleBookings;


}