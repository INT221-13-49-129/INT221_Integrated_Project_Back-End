package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.cars.models.Color;
import sit.int221.cars.models.Product;
import sit.int221.cars.repositories.ColorRepository;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorRepository colorRepository;

    @GetMapping("")
    public List<Color> color() {
        return colorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Color color(@PathVariable Long id) {
        return colorRepository.findById(id).orElseThrow(null);
    }

    @GetMapping("/{id}/productList")
    public List<Product> colorProductList(@PathVariable Long id) {
        return colorRepository.findById(id).orElseThrow(null).getProductList();
    }
}
