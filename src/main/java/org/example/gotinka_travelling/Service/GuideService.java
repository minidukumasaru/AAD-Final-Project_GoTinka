package org.example.gotinka_travelling.Service;

import org.example.gotinka_travelling.dto.GuideDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GuideService {
    void saveGuide(GuideDTO guideDTO);
    void saveGuideWithImage(GuideDTO guideDTO, MultipartFile image);
    List<GuideDTO> getAllGuides();
    GuideDTO getGuideById(int id);
    void updateGuide(int id, GuideDTO guideDTO);
    void updateGuideWithImage(int id, GuideDTO guideDTO, MultipartFile image);
    void deleteGuide(int id);
    Long getAvailableGuidesCount();
}