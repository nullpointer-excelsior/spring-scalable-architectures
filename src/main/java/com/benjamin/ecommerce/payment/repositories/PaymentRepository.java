package com.benjamin.ecommerce.payment.repositories;

import com.benjamin.ecommerce.payment.entities.PaymentEntity;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {}
