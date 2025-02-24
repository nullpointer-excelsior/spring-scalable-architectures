package com.benjamin.ecommerce.shared.core.purchase;

import com.benjamin.ecommerce.shared.core.purchase.dto.CreatePurchaseRequest;
import com.benjamin.ecommerce.shared.core.order.models.Order;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;
import com.benjamin.ecommerce.shared.core.shipping.models.Delivery;

public interface PurchaseProcessCoordinator {
    void process(CreatePurchaseRequest request);
    void process(Payment payment);
    void process(Order order);
    void process(Delivery delivery);
}
