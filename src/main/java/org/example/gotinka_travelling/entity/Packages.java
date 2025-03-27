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
@Table(name = "packages")
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageId;

    private String packageName;
    private String destination;
    private Double price;
    private String description;
    private Integer duration; // Days
    private Boolean active;
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL)
    private List<Booking> packageBookings;

    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL)
    private List<Offers> offers;

    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL)
    private List<GuideTour> guideTours;


    @OneToMany(mappedBy = "packages", cascade = CascadeType.ALL)
    private List<VehicleBooking> vehicleBookings;


}