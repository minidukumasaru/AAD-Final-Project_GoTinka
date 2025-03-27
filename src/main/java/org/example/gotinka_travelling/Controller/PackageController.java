package org.example.gotinka_travelling.Controller;

import org.example.gotinka_travelling.Service.PackageService;
import org.example.gotinka_travelling.Util.ResponseUtil;
import org.example.gotinka_travelling.dto.PackageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping("/save")
    public ResponseUtil savePackage(@RequestBody PackageDTO packageDTO) {
        packageService.savePackage(packageDTO);
        return new ResponseUtil(201,"Package Created Successfully",null);
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllPackages() {
        List<PackageDTO> packageDTOList = packageService.getAllPackages();
        return new ResponseUtil(200, "Packages Retrieved Successfully", packageDTOList);
    }

    @GetMapping("get/{id}")
    public ResponseUtil getPackageById(@PathVariable int id) {
        PackageDTO packageDTO = packageService.getPackageById(id);
        return new ResponseUtil(200, "Package Retrieved Successfully", packageDTO);
    }

    @GetMapping("/getNames")
    public ResponseUtil getPackageNames() {
        return new ResponseUtil(200, "Package Names Retrieved Successfully",
                packageService.getPackageNames());
    }

    @GetMapping("/getPackageId/{name}")
    public ResponseUtil getBundleByName(@PathVariable String name) {
        Integer packageId = packageService.getPackageIdByNames(name);
        return new ResponseUtil(200, "Bundle found",packageId);
    }

    @PutMapping("/update/{id}")
    public ResponseUtil updatePackage(@PathVariable int id, @RequestBody PackageDTO packageDTO) {
        packageService.updatePackage(id, packageDTO);
        return new ResponseUtil(200, "Package Updated Successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deletePackage(@PathVariable int id) {
        packageService.deletePackage(id);
        return new ResponseUtil(200, "Package Deleted Successfully", null);
    }
}
