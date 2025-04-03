package com.benjamin.ecommerce.shipping.repositories;

import com.benjamin.ecommerce.shipping.entities.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
