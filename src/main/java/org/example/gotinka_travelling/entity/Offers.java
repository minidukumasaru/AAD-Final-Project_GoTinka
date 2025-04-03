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
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;
    private String offerName;
    private Double discountPercentage;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private Boolean available;
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Packages packages;
}