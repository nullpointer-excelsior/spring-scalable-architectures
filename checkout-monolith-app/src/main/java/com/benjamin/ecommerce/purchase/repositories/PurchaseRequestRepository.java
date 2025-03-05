package com.benjamin.ecommerce.purchase.repositories;

import com.benjamin.ecommerce.purchase.entities.PurchaseRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequestEntity, Long> {}
