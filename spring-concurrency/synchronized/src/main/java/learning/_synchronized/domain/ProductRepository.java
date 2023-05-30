package learning._synchronized.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {
    Product save(Product entity);
    Optional<Product> findById(Long id);
}
