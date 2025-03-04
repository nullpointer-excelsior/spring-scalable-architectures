package com.benjamin.ecommerce.purchase;

import com.benjamin.ecommerce.purchase.dto.*;
import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.payment.models.Payment;
import com.benjamin.ecommerce.shipping.models.Shipping;

public interface PurchaseProcessCoordinator {
    PurchaseCreatedResponse process(CreatePurchaseRequest request);
    void process(Payment payment);
    void process(Order order);
    void process(Shipping shipping);
    void process(CompletePurchase completePurchase);
    void process(PurchaseError purchaseError);
}
