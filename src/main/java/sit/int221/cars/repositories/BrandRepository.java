package sit.int221.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.cars.models.Brand;
import sit.int221.cars.models.Product;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
