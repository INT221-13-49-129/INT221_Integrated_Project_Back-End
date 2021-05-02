package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.cars.models.Brand;
import sit.int221.cars.models.Product;
import sit.int221.cars.repositories.BrandRepository;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("")
    public List<Brand> brands() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    public Brand brands(@PathVariable Long id) {
        return brandRepository.findById(id).orElseThrow(null);
    }

    @GetMapping("/{id}/productList")
    public List<Product> brandsProductList(@PathVariable Long id) {
        return brandRepository.findById(id).orElseThrow(null).getProductList();
    }
}
