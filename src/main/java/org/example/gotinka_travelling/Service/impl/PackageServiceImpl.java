package org.example.gotinka_travelling.Service.impl;

import org.example.gotinka_travelling.Repo.PackageRepo;
import org.example.gotinka_travelling.Service.PackageService;
import org.example.gotinka_travelling.dto.PackageDTO;
import org.example.gotinka_travelling.entity.Packages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void savePackage(PackageDTO packageDTO) {
        if (packageDTO.getPackageId() != 0 && packageRepo.existsById(packageDTO.getPackageId())) {
            throw new RuntimeException("Package already exists");
        }
        Packages packages = modelMapper.map(packageDTO, Packages.class);
        packageRepo.save(packages);
    }


    @Override
    public List<PackageDTO> getAllPackages() {
        return packageRepo.findByIsDeletedFalse().stream()
                .map(packageEntity -> modelMapper.map(packageEntity, PackageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PackageDTO getPackageById(int id) {
        Packages packageEntity = packageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        return modelMapper.map(packageEntity, PackageDTO.class);
    }

    @Override
    public void updatePackage(int id, PackageDTO packageDTO) {
        Packages packageEntity = packageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        packageEntity.setPackageName(packageDTO.getPackageName());
        packageEntity.setDestination(packageDTO.getDestination());
        packageEntity.setPrice(packageDTO.getPrice());
        packageEntity.setDescription(packageDTO.getDescription());
        packageEntity.setDuration(packageDTO.getDuration());
        packageEntity.setActive(packageDTO.getActive());
        packageEntity.setDeleted(packageDTO.isDeleted());

        packageRepo.save(packageEntity);
    }

    @Override
    public void deletePackage(int id) {
        Packages packageEntity = packageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        packageEntity.setDeleted(true);
        packageRepo.save(packageEntity);
    }

    @Override
    public List<String> getPackageNames() {
        return packageRepo.findAllPackageNames();
    }

    @Override
    public Integer getPackageIdByNames(String name) {
        return packageRepo.findPackageIdByNames(name);
    }
}
