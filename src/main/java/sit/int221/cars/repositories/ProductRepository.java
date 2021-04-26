package sit.int221.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.cars.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
