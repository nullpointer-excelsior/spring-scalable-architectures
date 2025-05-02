package com.benjamin.ecommerce.carts.repositories;

import com.benjamin.ecommerce.carts.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<CartEntity, Long> {}
