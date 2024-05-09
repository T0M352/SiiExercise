package tomasz.morgas.siiZadanie.service.productService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tomasz.morgas.siiZadanie.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findByName(String name) {
        Optional<Product> result = productRepository.findById(name);
        Product theProduct = null;

        if (result.isPresent()) {
            theProduct = result.get();
        }
        else {
            return null;
        }

        return theProduct;
    }
    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

}
