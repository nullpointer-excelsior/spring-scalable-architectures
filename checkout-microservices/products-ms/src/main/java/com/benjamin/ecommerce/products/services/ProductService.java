package com.benjamin.ecommerce.products.services;

import com.benjamin.ecommerce.products.dto.UpdateProductQuantity;
import com.benjamin.ecommerce.products.mappers.ProductMapper;
import com.benjamin.ecommerce.products.repositories.ProductRepository;
import com.benjamin.ecommerce.products.ProductUseCases;
import com.benjamin.ecommerce.products.models.Product;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService implements ProductUseCases {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Observed(name = "products.all",
            contextualName = "getting-products",
            lowCardinalityKeyValues = {"productType", "productType2"})
    @Override
    public List<Product> findAll() {
        log.info("Products request");
        return productRepository.findAll().stream().map(productMapper::toModel).toList();
    }

    @Override
    public void updateProductQuantityBatch(List<UpdateProductQuantity> batch) {
        var stockSku = batch.stream().collect(Collectors.toMap(UpdateProductQuantity::sku, product -> product));
        var skus = batch.stream().map(UpdateProductQuantity::sku).toList();
        var products = productRepository.findAllById(skus);
        var productsUpdated = products.stream()
                .peek(product -> {
                    if (stockSku.containsKey(product.getSku())) {
                        var quantityToDiscount = stockSku.get(product.getSku()).quantity();
                        product.setQuantity(product.getQuantity() - quantityToDiscount);
                    }
                })
                .toList();
        productRepository.saveAll(productsUpdated);
    }

}
