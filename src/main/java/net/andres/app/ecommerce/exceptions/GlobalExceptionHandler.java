package net.andres.app.ecommerce.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFound(ProductNotFoundException ex){
        ErrorResponseDTO errorMessage = new ErrorResponseDTO(ex.getMessage());
        return ResponseEntity.status(404).body(errorMessage);
    }
}
