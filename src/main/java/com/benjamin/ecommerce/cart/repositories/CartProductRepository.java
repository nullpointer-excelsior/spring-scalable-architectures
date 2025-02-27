package com.benjamin.ecommerce.cart.repositories;

import com.benjamin.ecommerce.cart.entities.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
}
