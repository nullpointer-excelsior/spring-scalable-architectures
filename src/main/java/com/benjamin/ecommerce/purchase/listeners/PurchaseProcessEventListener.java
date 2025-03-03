package com.benjamin.ecommerce.purchase.listeners;

import com.benjamin.ecommerce.shared.integration.events.OrderCreatedEvent;
import com.benjamin.ecommerce.shared.integration.events.PaymentCreatedEvent;
import com.benjamin.ecommerce.purchase.PurchaseProcessCoordinator;
import com.benjamin.ecommerce.shared.integration.events.ShippingCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PurchaseProcessEventListener {

    @Autowired
    private PurchaseProcessCoordinator coordinator;

    @EventListener(PaymentCreatedEvent.class)
    public void onPaymentCreated(PaymentCreatedEvent event){
        log.info("payment-created: {}", event.getPayload());
        coordinator.process(event.getPayload());
    }

    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("order-created: {}", event.getPayload());
        coordinator.process(event.getPayload());
    }

    @EventListener(ShippingCreatedEvent.class)
    public void onShippingCreatedEvent(ShippingCreatedEvent event){
        log.info("shipping-created: {}", event.getPayload());
        coordinator.process(event.getPayload());
    }
}
