package net.andres.app.ecommerce.services;

import net.andres.app.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);

}
