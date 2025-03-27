package org.example.gotinka_travelling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Packages packages;

    private LocalDate bookingDate;
    private String status; // CONFIRMED, PENDING, CANCELLED

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;
}