package tomasz.morgas.siiZadanie.service;

import tomasz.morgas.siiZadanie.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findByName(String name);

    Product save(Product product);





}
