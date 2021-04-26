package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.cars.models.Brand;
import sit.int221.cars.models.Product;
import sit.int221.cars.repositories.BrandRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/brand")
    public List<Brand> brands() {
        return brandRepository.findAll();
    }

    @GetMapping("/brand/{id}")
    public Brand brands(@PathVariable Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @GetMapping("/brand/{id}/productList")
    public List<Product> brandsProductList(@PathVariable Long id) {
        return brandRepository.findById(id).orElse(null).getProductList();
    }
}
