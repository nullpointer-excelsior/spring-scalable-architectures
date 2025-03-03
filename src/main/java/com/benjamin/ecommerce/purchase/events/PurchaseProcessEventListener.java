package com.benjamin.ecommerce.purchase.events;

import com.benjamin.ecommerce.payment.events.PaymentCreatedEvent;
import com.benjamin.ecommerce.purchase.PurchaseProcessCoordinator;
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
}
