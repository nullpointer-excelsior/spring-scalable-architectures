package com.benjamin.ecommerce.products.repositories;

import com.benjamin.ecommerce.products.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {}
