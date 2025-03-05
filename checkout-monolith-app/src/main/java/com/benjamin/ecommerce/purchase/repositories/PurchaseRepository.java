package com.benjamin.ecommerce.purchase.repositories;

import com.benjamin.ecommerce.purchase.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

}
