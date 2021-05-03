package sit.int221.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.int221.cars.exceptions.ExceptionResponse;
import sit.int221.cars.exceptions.ProductException;
import sit.int221.cars.models.Product;
import sit.int221.cars.repositories.ProductRepository;
import sit.int221.cars.services.StorageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageService storageService;

    @GetMapping("")
    public List<Product> product() {
        return productRepository.findAll();
    }

    @GetMapping("/maxid")
    public long maxProductId() {
        return productRepository.MaxProductId();
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

    @PostMapping(value = "/add/withimg",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product createProImg(@RequestParam(value = "file",required = false) MultipartFile fileImg,@RequestPart Product newProduct) {
        if(productRepository.findById(newProduct.getProductid()).orElse(null) != null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_ALREADY_EXIST,"id :product {"+newProduct.getProductid()+"} does already exist !!");
        }else if (productRepository.findByProductname(newProduct.getProductname()) != null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_NAME_ALREADY_EXIST,"name :product {"+newProduct.getProductname()+"} does already exist !!");
        }else if(fileImg == null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_INCOMPLETE_DELIVERY,"id :id {"+newProduct.getProductid()+"} no image !!");
        }
        try {
            newProduct.setImg(storageService.store(fileImg, String.valueOf(newProduct.getProductid())));
        } catch (Exception e) {
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_INCOMPLETE_DELIVERY,"id :id {"+newProduct.getProductid()+"}"+ e.getMessage() +"!!");
        }
        return productRepository.save(newProduct);
    }

    @PutMapping("/edit/{id}")
    public Product update(@RequestPart Product updateProduct,@PathVariable Long id) {
        Product prod = productRepository.findById(id).orElse(null);
        if( prod == null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,"id :product {"+id+"} does not exist !!");
        }else if (productRepository.findByProductname(updateProduct.getProductname()) != null && prod.getProductid() != productRepository.findByProductname(updateProduct.getProductname()).getProductid()){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_NAME_ALREADY_EXIST,"name :product {"+updateProduct.getProductname()+"} does already exist !!");
        }else{
            prod.setProductname(updateProduct.getProductname());
            prod.setPower(updateProduct.getPower());
            prod.setTorque(updateProduct.getTorque());
            prod.setWeight(updateProduct.getWeight());
            prod.setTransmission(updateProduct.getTransmission());
            prod.setYom(updateProduct.getYom());
            prod.setDescription(updateProduct.getDescription());
            prod.setBrand(updateProduct.getBrand());
            prod.setColorList(updateProduct.getColorList());
        }
        return productRepository.save(prod);
    }

    @PutMapping(value = "/edit/{id}/withimg",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product updateImg(@RequestParam(value = "file",required = false) MultipartFile fileImg,@RequestPart Product updateProduct,@PathVariable Long id) {
        Product prod = productRepository.findById(id).orElse(null);
        if( prod == null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,"id :product {"+id+"} does not exist !!");
        }else if (productRepository.findByProductname(updateProduct.getProductname()) != null && prod.getProductid() != productRepository.findByProductname(updateProduct.getProductname()).getProductid()){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_NAME_ALREADY_EXIST,"name :product {"+updateProduct.getProductname()+"} does already exist !!");
        }else {
            prod.setProductname(updateProduct.getProductname());
            prod.setPower(updateProduct.getPower());
            prod.setTorque(updateProduct.getTorque());
            prod.setWeight(updateProduct.getWeight());
            prod.setTransmission(updateProduct.getTransmission());
            prod.setYom(updateProduct.getYom());
            prod.setDescription(updateProduct.getDescription());
            prod.setBrand(updateProduct.getBrand());
            prod.setColorList(updateProduct.getColorList());
            if(fileImg != null){
                try {
                    storageService.delete(prod.getImg());
                    prod.setImg(storageService.store(fileImg, String.valueOf(updateProduct.getProductid())));
                } catch (Exception e) {
                    throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_INCOMPLETE_DELIVERY,"id :id {"+updateProduct.getProductid()+"}"+ e.getMessage() +"!!");
                }
            }

        }
        return productRepository.save(prod);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        Product prod = productRepository.findById(id).orElse(null);
        if(prod == null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,"id :product {"+id+"} does not exist !!");
        }
        try {
            storageService.delete(prod.getImg());
        } catch (IOException e) {
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_INCOMPLETE_DELETION,"id :id {"+prod.getProductid()+"} "+ e.getMessage() +"!!");
        }
        productRepository.delete(prod);
    }

}
