package com.benjamin.ecommerce.cart.controllers;

import com.benjamin.ecommerce.shared.core.cart.CartUseCases;
import com.benjamin.ecommerce.shared.core.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.shared.core.cart.dto.UpdateProductsRequest;
import com.benjamin.ecommerce.shared.core.cart.models.Cart;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartRestController {

  @Autowired private CartUseCases cartUseCases;

  @PostMapping
  public Cart create(@RequestBody @Valid CreateCartRequest request) {
    return this.cartUseCases.create(request);
  }

  @PutMapping("{id}/products")
  public Cart updateProducts(
      @PathVariable("id") String id, @Valid @RequestBody UpdateProductsRequest request) {
    return this.cartUseCases.updateProducts(id, request.products());
  }
}
