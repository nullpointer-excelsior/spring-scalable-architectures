package com.benjamin.ecommerce.products;

import com.benjamin.ecommerce.products.models.Product;

import java.util.List;

public interface ProductUseCases {
    List<Product> findAll();
    void updateQuantity(String sku, Integer quantity);
}
