package org.example.gotinka_travelling.Service;

import org.example.gotinka_travelling.dto.OffersDTO;
import java.util.List;

public interface OffersService {
    void saveOffers(OffersDTO offersDTO);  // Save a new offer
    List<OffersDTO> getAllOffers();  // Get all offers
    void updateOffer(int id, OffersDTO offersDTO);  // Update an existing offer
    void deleteOffer(int id);  // Soft delete an offer
}
