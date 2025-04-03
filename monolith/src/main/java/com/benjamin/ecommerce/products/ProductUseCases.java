package com.benjamin.ecommerce.products;

import com.benjamin.ecommerce.products.dto.UpdateProductQuantity;
import com.benjamin.ecommerce.products.models.Product;

import java.util.List;

public interface ProductUseCases {
    List<Product> findAll();
    void updateProductQuantityBatch(List<UpdateProductQuantity> batch);
}
