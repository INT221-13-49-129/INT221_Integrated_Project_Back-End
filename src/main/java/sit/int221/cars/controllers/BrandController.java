package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.cars.models.Brand;
import sit.int221.cars.repositories.BrandRepository;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/brand")
    public List<Brand> brands() {
        return brandRepository.findAll();
    }
}
