package net.andres.app.ecommerce.services;

import net.andres.app.ecommerce.entities.Product;
import net.andres.app.ecommerce.exceptions.ProductNotFoundException;
import net.andres.app.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceManager implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));
    }

    @Override
    public Product save(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productFound = this.repository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(id));

        if(product.getName() != null){
            productFound.setName(product.getName());
        }
        if(product.getPrice() != null){
            productFound.setPrice(product.getPrice());
        }
        return this.repository.save(productFound);
    }

    @Override
    public void delete(Long id) {
        Product productFound = this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        this.repository.delete(productFound);
    }
}
