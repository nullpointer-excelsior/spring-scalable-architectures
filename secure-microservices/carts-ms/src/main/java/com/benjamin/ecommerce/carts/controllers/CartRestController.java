package com.benjamin.ecommerce.carts.controllers;

import com.benjamin.ecommerce.carts.CartUseCases;
import com.benjamin.ecommerce.carts.dto.CreateCartRequest;
import com.benjamin.ecommerce.carts.dto.UpdateProductsRequest;
import com.benjamin.ecommerce.carts.models.Cart;
import com.benjamin.ecommerce.carts.models.CartProduct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
