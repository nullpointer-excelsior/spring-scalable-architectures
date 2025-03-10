package com.benjamin.ecommerce.cart.controllers;

import com.benjamin.ecommerce.cart.CartUseCases;
import com.benjamin.ecommerce.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.cart.dto.UpdateProductsRequest;
import com.benjamin.ecommerce.cart.models.Cart;
import com.benjamin.ecommerce.cart.models.CartProduct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.PATCH, RequestMethod.DELETE})
@RequestMapping("/carts")
public class CartRestController {

    @Autowired
    private CartUseCases cartUseCases;

    @GetMapping("{id}")
    public Cart getById(@PathVariable("id") Long id) {
        return this.cartUseCases.findById(id);
    }

    @PostMapping
    public Cart create(@RequestBody @Valid CreateCartRequest request) {
        return this.cartUseCases.create(request);
    }

    @PutMapping("{id}/products")
    public Cart updateProducts(@PathVariable("id") Long id, @Valid @RequestBody UpdateProductsRequest request) {
        return this.cartUseCases.updateProducts(id, request.products());
    }

    @PatchMapping("{id}/products/{sku}")
    public CartProduct updateProduct(@PathVariable("id") Long id, @PathVariable("sku") String sku, @RequestBody CartProduct request) {
        return cartUseCases.updateProduct(id, sku, request);
    }

    @DeleteMapping("{id}/products/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id, @PathVariable("sku") String sku) {
        cartUseCases.deleteProduct(id, sku);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
        var errors = new HashMap<String, String>();
        errors.put("message", ex.getMessage());
        return errors;
    }
}
