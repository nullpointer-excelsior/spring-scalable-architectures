package com.benjamin.ecommerce.shared.core.checkout;

import com.benjamin.ecommerce.shared.core.checkout.dto.CreateCheckoutProcessRequest;
import com.benjamin.ecommerce.shared.core.order.models.Order;
import com.benjamin.ecommerce.shared.core.payment.models.Payment;
import com.benjamin.ecommerce.shared.core.shipping.models.Delivery;

public interface CheckoutProcessCoordinator {
    void process(CreateCheckoutProcessRequest request);
    void process(Payment payment);
    void process(Order order);
    void process(Delivery delivery);
}
