package org.example.gotinka_travelling.Controller;

import org.example.gotinka_travelling.Service.GuideService;
import org.example.gotinka_travelling.Util.ResponseUtil;
import org.example.gotinka_travelling.dto.GuideDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/guide")
public class GuideController {

    @Autowired
    private GuideService guideService;

    @GetMapping("/getAll")
    public ResponseUtil getAllGuides() {
        List<GuideDTO> guideDTOList = guideService.getAllGuides();
        return new ResponseUtil(200, "Guides Retrieved Successfully", guideDTOList);
    }

    @GetMapping("/get/{id}")
    public ResponseUtil getGuideById(@PathVariable int id) {
        GuideDTO guideDTO = guideService.getGuideById(id);
        return new ResponseUtil(200, "Guide Retrieved Successfully", guideDTO);
    }

    @PostMapping("/save")
    public ResponseUtil saveGuide(@RequestBody GuideDTO guideDTO) {
        guideService.saveGuide(guideDTO);
        return new ResponseUtil(201, "Guide Saved Successfully", null);
    }

    @PostMapping(value = "/saveWithImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil saveGuideWithImage(
            @RequestPart("guide") GuideDTO guideDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            guideService.saveGuideWithImage(guideDTO, image);
        } else {
            guideService.saveGuide(guideDTO);
        }
        return new ResponseUtil(201, "Guide Saved Successfully", null);
    }

    @PutMapping("/update/{id}")
    public ResponseUtil updateGuide(@PathVariable int id, @RequestBody GuideDTO guideDTO) {
        guideService.updateGuide(id, guideDTO);
        return new ResponseUtil(200, "Guide Updated Successfully", null);
    }

    @PutMapping(value = "/updateWithImage/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil updateGuideWithImage(
            @PathVariable int id,
            @RequestPart("guide") GuideDTO guideDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            guideService.updateGuideWithImage(id, guideDTO, image);
        } else {
            guideService.updateGuide(id, guideDTO);
        }
        return new ResponseUtil(200, "Guide Updated Successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteGuide(@PathVariable int id) {
        guideService.deleteGuide(id);
        return new ResponseUtil(200, "Guide Deleted Successfully", null);
    }
}