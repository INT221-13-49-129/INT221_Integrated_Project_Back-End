package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.cars.models.Color;
import sit.int221.cars.models.Product;
import sit.int221.cars.repositories.ColorRepository;

import java.util.List;

@RestController
public class ColorController {
    @Autowired
    private ColorRepository colorRepository;

    @GetMapping("/color")
    public List<Color> color() {
        return colorRepository.findAll();
    }

    @GetMapping("/color/{id}")
    public Color color(@PathVariable Long id) {
        return colorRepository.findById(id).orElseThrow(null);
    }

    @GetMapping("/color/{id}/productList")
    public List<Product> colorProductList(@PathVariable Long id) {
        return colorRepository.findById(id).orElseThrow(null).getProductList();
    }
}
