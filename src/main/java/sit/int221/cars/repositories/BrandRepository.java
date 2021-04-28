package sit.int221.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.cars.models.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
