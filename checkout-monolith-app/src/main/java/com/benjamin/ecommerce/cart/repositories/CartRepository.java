package com.benjamin.ecommerce.cart.repositories;

import com.benjamin.ecommerce.cart.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<CartEntity, Long> {}
