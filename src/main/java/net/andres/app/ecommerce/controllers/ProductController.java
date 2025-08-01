package net.andres.app.ecommerce.controllers;



import net.andres.app.ecommerce.entities.Product;
import net.andres.app.ecommerce.services.ProductServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceManager serviceManager;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> findAllProducts(){
        return ResponseEntity.ok().body(this.serviceManager.findAll());
    };

    @PostMapping()
    @Transactional
    public ResponseEntity<Product> save(@RequestBody Product product){
        Product savedProduct = serviceManager.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedProduct);
    }


    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Product> getByIdProduct(@PathVariable Long id){
        Product productFound = this.serviceManager.findById(id);
        if(productFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productFound);
    }


    @PutMapping("/{id}")
    @Transactional()
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product updateProduct = this.serviceManager.update(id,product);
        if( updateProduct == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        this.serviceManager.delete(id);
        return ResponseEntity.ok().body("Product deleted correctly");
    }



}
