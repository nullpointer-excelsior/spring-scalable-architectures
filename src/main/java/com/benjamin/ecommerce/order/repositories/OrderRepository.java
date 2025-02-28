package com.benjamin.ecommerce.order.repositories;

import com.benjamin.ecommerce.order.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
