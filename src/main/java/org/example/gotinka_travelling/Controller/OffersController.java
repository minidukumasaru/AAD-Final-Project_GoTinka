package org.example.gotinka_travelling.Controller;

import org.example.gotinka_travelling.Service.OffersService;
import org.example.gotinka_travelling.Util.ResponseUtil;
import org.example.gotinka_travelling.dto.OffersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/offers")
public class OffersController {
    @Autowired
    private OffersService offersService;

    @PostMapping("/save")
    public ResponseUtil saveOffers(@RequestBody OffersDTO offersDTO) {
        System.out.println(offersDTO);
        offersService.saveOffers(offersDTO);
        return new ResponseUtil(201,"Offer Create Successfully",null);
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllOffers() {
        return new ResponseUtil(200, "Success",
                offersService.getAllOffers());
    }

    @PutMapping("update")
    public ResponseUtil updateOffer(@RequestBody OffersDTO offersDTO) {
        offersService.updateOffer(offersDTO);
        return new ResponseUtil(200, "Offer Updated Successfully", null);
    }

    @DeleteMapping("delete/{id}")
    public ResponseUtil deleteOffer(@PathVariable int id) {
        offersService.deleteOffer(id);
        return new ResponseUtil(200, "Offer Soft Deleted Successfully", null);
    }
}
