package sit.int221.cars.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.cars.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByProductname(String prodname);
    Page<Product> findAllByProductnameContainingOrDescriptionContaining(String searchData1,String searchData2,Pageable pageable);
    @Query("select p from Product p where p.brand.brandid = ?1")
    Page<Product> findAllByBrandID(Long brandId,Pageable pageable);
    @Query("SELECT max(p.productid) FROM Product p")
    long MaxProductId();

}
