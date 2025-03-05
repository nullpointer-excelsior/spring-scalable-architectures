package com.benjamin.ecommerce.products.restcontrollers;

import com.benjamin.ecommerce.products.ProductUseCases;
import com.benjamin.ecommerce.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ProductUseCases productUseCases;

    @GetMapping
    public List<Product> getAll() {
        return this.productUseCases.findAll();
    }
}
