package com.benjamin.ecommerce.payment.repositories;

import com.benjamin.ecommerce.payment.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {}
