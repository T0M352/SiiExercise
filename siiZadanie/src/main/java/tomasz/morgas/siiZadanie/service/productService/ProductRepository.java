package tomasz.morgas.siiZadanie.service.productService;

import org.springframework.data.jpa.repository.JpaRepository;
import tomasz.morgas.siiZadanie.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
