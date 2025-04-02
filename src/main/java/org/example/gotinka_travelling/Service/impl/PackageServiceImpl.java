package org.example.gotinka_travelling.Service.impl;

import org.example.gotinka_travelling.Repo.PackageRepo;
import org.example.gotinka_travelling.Service.PackageService;
import org.example.gotinka_travelling.dto.GuideDTO;
import org.example.gotinka_travelling.dto.PackageDTO;
import org.example.gotinka_travelling.entity.Packages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${file.upload-dir:uploads/Packages}")
    private String uploadDir;

    @Override
    public void savePackage(PackageDTO packageDTO) {
        if (packageDTO.getPackageId() != 0 && packageRepo.existsById(packageDTO.getPackageId())) {
            throw new RuntimeException("Package already exists");
        }
        Packages packages = modelMapper.map(packageDTO, Packages.class);
        packageRepo.save(packages);
    }
    @Override
    public void savePackageWithImage(PackageDTO packageDTO, MultipartFile image) {
        try {
            String fileName = saveImage(image);
            packageDTO.setImagePath(fileName);
            savePackage(packageDTO);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }
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

        // Only update imagePath if provided in the DTO
        if (packageDTO.getImagePath() != null && !packageDTO.getImagePath().isEmpty()) {
            packageEntity.setImagePath(packageDTO.getImagePath());
        }
        packageRepo.save(packageEntity);
    }
    @Override
    public void updatePackageWithImage(int id, PackageDTO packageDTO, MultipartFile image) {
        try {
            if (image != null && !image.isEmpty()) {
                String fileName = saveImage(image);
                packageDTO.setImagePath(fileName);
            }
            updatePackage(id, packageDTO);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update image: " + e.getMessage());
        }
    }
    private String saveImage(MultipartFile file) throws IOException {
        // Create directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate a unique file name
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(uniqueFileName);

        // Save the file
        Files.copy(file.getInputStream(), filePath);

        return uniqueFileName;
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
    @Override
    public Long getActivePackagesCount() {
        return packageRepo.countActivePackages();
    }
}
