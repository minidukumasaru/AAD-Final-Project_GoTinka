package org.example.gotinka_travelling.Service.impl;

import org.example.gotinka_travelling.Repo.OffersRepo;
import org.example.gotinka_travelling.Repo.PackageRepo;
import org.example.gotinka_travelling.Service.OffersService;
import org.example.gotinka_travelling.dto.OffersDTO;
import org.example.gotinka_travelling.entity.Offers;
import org.example.gotinka_travelling.entity.Packages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffersServiceImpl implements OffersService {

    private final OffersRepo offersRepo;
    private final PackageRepo packageRepo;
    private final ModelMapper modelMapper;

    public OffersServiceImpl(OffersRepo offersRepo, PackageRepo packageRepo, ModelMapper modelMapper) {
        this.offersRepo = offersRepo;
        this.packageRepo = packageRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OffersDTO> getAllOffers() {
        return offersRepo.findByIsDeletedFalse().stream()
                .map(offer -> modelMapper.map(offer, OffersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveOffers(OffersDTO offersDTO) {
        Offers offer = modelMapper.map(offersDTO, Offers.class);
        offer.setDeleted(false);  // Ensure the offer is not deleted by default

        Packages packages = packageRepo.findById(offersDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));
        offer.setPackages(packages);

        offersRepo.save(offer);
    }

    @Override
    public void updateOffer(int id, OffersDTO offersDTO) {
        Offers existingOffer = offersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        existingOffer.setOfferName(offersDTO.getOfferName());
        existingOffer.setDiscountPercentage(offersDTO.getDiscountPercentage());
        existingOffer.setValidFrom(offersDTO.getValidFrom());
        existingOffer.setValidUntil(offersDTO.getValidUntil());
        existingOffer.setAvailable(offersDTO.getAvailable());

        Packages packages = packageRepo.findById(offersDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));
        existingOffer.setPackages(packages);

        offersRepo.save(existingOffer);
    }

    @Override
    public void deleteOffer(int id) {
        Offers offer = offersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setDeleted(true);  // Soft delete
        offersRepo.save(offer);
    }
}
