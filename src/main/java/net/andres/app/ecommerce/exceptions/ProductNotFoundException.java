package net.andres.app.ecommerce.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Producto con ID: "+id+" no encontrado");
    }
}
