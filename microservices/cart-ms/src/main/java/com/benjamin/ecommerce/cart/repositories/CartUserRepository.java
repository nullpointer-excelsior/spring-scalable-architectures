package com.benjamin.ecommerce.cart.repositories;

import com.benjamin.ecommerce.cart.entities.CartUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartUserRepository extends JpaRepository<CartUserEntity, Long> {
}
