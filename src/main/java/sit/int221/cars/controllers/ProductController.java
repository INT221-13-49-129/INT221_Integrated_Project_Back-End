package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sit.int221.cars.exceptions.ExceptionResponse;
import sit.int221.cars.exceptions.ProductException;
import sit.int221.cars.models.Product;
import sit.int221.cars.repositories.ProductRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<Product> product() {
        return productRepository.findAll();
    }

    @GetMapping("/page")
    public Page<Product> productWithPage(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "productid") String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy) );
        Page<Product> pageResult = productRepository.findAll(pageable);
        return pageResult;
    }

    @GetMapping("/page/search")
    public Page<Product> productWithPageSearch (
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "productid") String sortBy,
            @RequestParam(defaultValue = "") String searchData) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy) );
        Page<Product> pageResult = productRepository.findAllByProductnameContainingOrDescriptionContaining(searchData,searchData,pageable);
        return pageResult;
    }

    @GetMapping("/page/brand")
    public Page<Product> productWithPageBrand (
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "productid") String sortBy,
            @RequestParam(defaultValue = "") Long brandId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy) );
        Page<Product> pageResult = productRepository.findAllByBrandID(brandId,pageable);
        return pageResult;
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable Long id) {
        Product prod = productRepository.findById(id).orElse(null);
        if(prod == null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,"id :product {"+id+"} does not exist !!");
        }
        return prod;
    }

    @GetMapping("/count")
    public long productCount() {
        return productRepository.count();
    }

    @PostMapping("/add")
    public Product create(@RequestBody Product newProduct) {
        if(productRepository.findById(newProduct.getProductid()).orElse(null) != null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_ALREADY_EXIST,"id :product {"+newProduct.getProductid()+"} does already exist !!");
        }else if (productRepository.findByProductname(newProduct.getProductname()) != null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_NAME_ALREADY_EXIST,"name :product {"+newProduct.getProductname()+"} does already exist !!");
        }
        return productRepository.save(newProduct);
    }

    @PutMapping("/edit/{id}")
    public Product update(@RequestBody Product updateProduct,@PathVariable Long id) {
        Product prod = productRepository.findById(id).orElse(null);
        if( prod == null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,"id :product {"+id+"} does not exist !!");
        }else {
            prod.setProductname(updateProduct.getProductname());
            prod.setPower(updateProduct.getPower());
            prod.setTorque(updateProduct.getTorque());
            prod.setTransmission(updateProduct.getTransmission());
            prod.setYom(updateProduct.getYom());
            prod.setDescription(updateProduct.getDescription());
            prod.setBrand(updateProduct.getBrand());
            prod.setColorList(updateProduct.getColorList());
        }
        return productRepository.save(prod);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        Product prod = productRepository.findById(id).orElse(null);
        if(prod == null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,"id :product {"+id+"} does not exist !!");
        }
        productRepository.delete(prod);
    }

}
