package org.example.gotinka_travelling.Service.impl;

import org.example.gotinka_travelling.Service.GuideService;
import org.example.gotinka_travelling.Repo.GuideRepository;
import org.example.gotinka_travelling.dto.GuideDTO;
import org.example.gotinka_travelling.entity.Guide;
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
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideRepository guideRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${file.upload-dir:uploads/guides}")
    private String uploadDir;

    @Override
    public List<GuideDTO> getAllGuides() {
        return guideRepo.findByIsDeletedFalse().stream()
                .map(guide -> modelMapper.map(guide, GuideDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GuideDTO getGuideById(int id) {
        Guide guide = guideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guide not found"));
        return modelMapper.map(guide, GuideDTO.class);
    }

    @Override
    public void saveGuide(GuideDTO guideDTO) {
        Guide guide = modelMapper.map(guideDTO, Guide.class);
        guide.setDeleted(false);
        guideRepo.save(guide);
    }

    @Override
    public void saveGuideWithImage(GuideDTO guideDTO, MultipartFile image) {
        try {
            String fileName = saveImage(image);
            guideDTO.setImagePath(fileName);
            saveGuide(guideDTO);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }
    }

    @Override
    public void updateGuide(int id, GuideDTO guideDTO) {
        Guide guide = guideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guide not found"));

        guide.setName(guideDTO.getName());
        guide.setContact(guideDTO.getContact());
        guide.setLanguages(guideDTO.getLanguages());
        guide.setRating(guideDTO.getRating());
        guide.setAvailable(guideDTO.getAvailable());

        // Only update imagePath if provided in the DTO
        if (guideDTO.getImagePath() != null && !guideDTO.getImagePath().isEmpty()) {
            guide.setImagePath(guideDTO.getImagePath());
        }

        guideRepo.save(guide);
    }

    @Override
    public void updateGuideWithImage(int id, GuideDTO guideDTO, MultipartFile image) {
        try {
            if (image != null && !image.isEmpty()) {
                String fileName = saveImage(image);
                guideDTO.setImagePath(fileName);
            }
            updateGuide(id, guideDTO);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update image: " + e.getMessage());
        }
    }

    @Override
    public void deleteGuide(int id) {
        Guide guide = guideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guide not found"));

        guide.setDeleted(true);  // Soft delete
        guideRepo.save(guide);
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
}