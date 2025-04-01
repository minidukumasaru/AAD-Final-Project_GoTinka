package org.example.gotinka_travelling.Controller;

import org.example.gotinka_travelling.Service.OffersService;
import org.example.gotinka_travelling.Service.UserService;
import org.example.gotinka_travelling.Util.JwtUtil;
import org.example.gotinka_travelling.Util.ResponseUtil;
import org.example.gotinka_travelling.dto.OffersDTO;
import org.example.gotinka_travelling.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/offers")
public class OffersController {
    private final UserService userService;

    private final OffersService offersService;

    public OffersController(OffersService offersService,UserService userService) {
        this.offersService = offersService;//constructor injection
            this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllOffers() {
        List<OffersDTO> offersList = offersService.getAllOffers();
        return new ResponseUtil(200, "Offers Retrieved Successfully", offersList);
    }

    @PostMapping("/save")
    public ResponseUtil saveOffers(@RequestBody OffersDTO offersDTO) {
        offersService.saveOffers(offersDTO);
        return new ResponseUtil(201, "Offer Saved Successfully", null);
    }

    @PutMapping("/update/{id}")
    public ResponseUtil updateOffer(@PathVariable int id, @RequestBody OffersDTO offersDTO) {
        offersService.updateOffer(id, offersDTO);
        return new ResponseUtil(200, "Offer Updated Successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteOffer(@PathVariable int id) {
        offersService.deleteOffer(id);
        return new ResponseUtil(200, "Offer Deleted Successfully", null);
    }
    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        // Get current logged-in user's details
        User currentUser = userService.getCurrentUser();
        stats.put("userName", currentUser.getName());
        stats.put("userEmail", currentUser.getEmail());

        return stats;
    }
}
