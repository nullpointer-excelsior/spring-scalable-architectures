package com.benjamin.ecommerce.carts.repositories;

import com.benjamin.ecommerce.carts.entities.CartUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartUserRepository extends JpaRepository<CartUserEntity, Long> {
}
