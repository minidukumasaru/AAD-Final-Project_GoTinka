package org.example.gotinka_travelling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OffersDTO {
    private int offerId;
    private String offerName;
    private Double discountPercentage;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private int packageId;
    private boolean isDeleted = false;
}
