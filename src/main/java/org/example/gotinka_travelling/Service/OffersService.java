package org.example.gotinka_travelling.Service;

import org.example.gotinka_travelling.dto.OffersDTO;

import java.util.List;

public interface OffersService {
    void saveOffers(OffersDTO offersDTO);
    List<OffersDTO> getAllOffers();
    void updateOffer(OffersDTO offersDTO);
    void deleteOffer(int id);

}
