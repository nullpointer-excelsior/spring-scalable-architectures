package com.benjamin.ecommerce.shipping.repositories;

import com.benjamin.ecommerce.shipping.entities.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingRepository extends JpaRepository<ShippingEntity, Long> {
    Optional<ShippingEntity> findByPurchaseId(long id);
}
