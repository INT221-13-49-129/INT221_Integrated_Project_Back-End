package sit.int221.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.cars.models.Color;

public interface ColorRepository extends JpaRepository<Color,Long> {
}
