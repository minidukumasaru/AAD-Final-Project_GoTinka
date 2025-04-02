package org.example.gotinka_travelling.Service;

import org.example.gotinka_travelling.dto.GuideDTO;
import org.example.gotinka_travelling.dto.PackageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PackageService {
    void savePackage(PackageDTO packageDTO);
    void savePackageWithImage(PackageDTO packageDTO, MultipartFile image);
    List<PackageDTO> getAllPackages();
    PackageDTO getPackageById(int id);
    void updatePackage(int id, PackageDTO packageDTO);
    void updatePackageWithImage(int id,PackageDTO packageDTO, MultipartFile image);
    void deletePackage(int id);
    List<String> getPackageNames();
    Integer getPackageIdByNames(String name);
    Long getActivePackagesCount();
}
