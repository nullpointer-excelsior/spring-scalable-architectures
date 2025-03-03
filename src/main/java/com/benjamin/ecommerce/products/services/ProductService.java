package com.benjamin.ecommerce.products.services;

import com.benjamin.ecommerce.products.mappers.ProductMapper;
import com.benjamin.ecommerce.products.repositories.ProductRepository;
import com.benjamin.ecommerce.products.ProductUseCases;
import com.benjamin.ecommerce.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductUseCases {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toModel)
                .toList();
    }

    @Override
    public void updateQuantity(String sku, Integer quantity) {
        var product = productRepository.findById(sku)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with SKU: " + sku));
        product.setQuantity(quantity);
        productRepository.save(product);
    }
}
