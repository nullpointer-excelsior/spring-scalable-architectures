package com.benjamin.ecommerce.shared.core.products;

import com.benjamin.ecommerce.shared.core.products.models.Product;

import java.util.List;

public interface ProductUseCases {
    List<Product> findAll();
    void updateQuantity(String sku, Integer quantity);
}
