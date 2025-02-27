package com.benjamin.ecommerce.cart.controllers;

import com.benjamin.ecommerce.shared.core.cart.CartUseCases;
import com.benjamin.ecommerce.shared.core.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.shared.core.cart.dto.UpdateProductsRequest;
import com.benjamin.ecommerce.shared.core.cart.models.Cart;
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
@RequestMapping("/carts")
public class CartRestController {

  @Autowired private CartUseCases cartUseCases;

  @GetMapping("{id}")
  public Cart getById(@PathVariable("id") Long id) {
    return this.cartUseCases.findById(id);
  }

  @PostMapping
  public Cart create(@RequestBody @Valid CreateCartRequest request) {
    return this.cartUseCases.create(request);
  }

  @PutMapping("{id}/products")
  public Cart updateProducts(
      @PathVariable("id") Long id, @Valid @RequestBody UpdateProductsRequest request) {
    return this.cartUseCases.updateProducts(id, request.products());
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
