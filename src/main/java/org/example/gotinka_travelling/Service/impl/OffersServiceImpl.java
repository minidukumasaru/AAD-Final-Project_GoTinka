package org.example.gotinka_travelling.Service.impl;

import org.example.gotinka_travelling.Repo.OffersRepo;
import org.example.gotinka_travelling.Repo.PackageRepo;
import org.example.gotinka_travelling.Service.OffersService;
import org.example.gotinka_travelling.dto.OffersDTO;
import org.example.gotinka_travelling.entity.Offers;
import org.example.gotinka_travelling.entity.Packages;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffersServiceImpl implements OffersService {

    @Autowired
    private OffersRepo offersRepo;

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveOffers(OffersDTO offersDTO) {
        // Check if the offer already exists
        if (offersDTO.getOfferId() != 0 && offersRepo.existsById(offersDTO.getOfferId())) {
            throw new RuntimeException("Offer already exists");
        }

        // Map DTO to entity
        Offers offers = modelMapper.map(offersDTO, Offers.class);

        // Fetch and assign package
        Packages packages = packageRepo.findById(offersDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));
        offers.setPackages(packages);
        System.out.println(offersDTO.getPackageId());

        // Save to database
        offersRepo.save(offers);
    }

    @Override
    public List<OffersDTO> getAllOffers() {
        List<Offers> offersList = offersRepo.findByIsDeletedFalse();
        return modelMapper.map(offersList, new TypeToken<List<OffersDTO>>() {}.getType());
    }

    @Override
    public void updateOffer(OffersDTO offersDTO) {
        Offers existingOffer = offersRepo.findById(offersDTO.getOfferId())
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        existingOffer.setOfferName(offersDTO.getOfferName());
        existingOffer.setDiscountPercentage(offersDTO.getDiscountPercentage());
        existingOffer.setValidFrom(offersDTO.getValidFrom());
        existingOffer.setValidUntil(offersDTO.getValidUntil());

        Packages packages = packageRepo.findById(offersDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));
        existingOffer.setPackages(packages);

        offersRepo.save(existingOffer);
    }

    @Override
    public void deleteOffer(int id) {
        Offers offer = offersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        offer.setDeleted(true);
        offersRepo.save(offer);
    }
}
