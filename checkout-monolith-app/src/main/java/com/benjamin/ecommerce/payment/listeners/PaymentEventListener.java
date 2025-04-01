package com.benjamin.ecommerce.payment.listeners;

import com.benjamin.ecommerce.payment.services.PaymentService;
import com.benjamin.ecommerce.shared.integration.events.PaymentCreatedEvent;
import com.benjamin.ecommerce.shared.integration.events.CreatePaymentEvent;
import com.benjamin.ecommerce.shared.integration.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentEventListener {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EventBus eventBus;

    @EventListener(CreatePaymentEvent.class)
    public void onPaymentRequestEvent(CreatePaymentEvent event) {
        log.info("create-payment: {}", event);
        var payment = this.paymentService.create(event.getPayload());
        this.eventBus.dispatch(new PaymentCreatedEvent(payment));
    }
}
