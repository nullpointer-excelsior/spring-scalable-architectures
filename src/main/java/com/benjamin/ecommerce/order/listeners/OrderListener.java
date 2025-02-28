package com.benjamin.ecommerce.order.listeners;

import com.benjamin.ecommerce.shared.core.order.OrderUseCases;
import com.benjamin.ecommerce.shared.core.purchase.events.CreateOrderEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OrderListener {

    @Autowired
    private OrderUseCases orderUseCases;

    @EventListener(CreateOrderEvent.class)
    public void onCreateOrderEvent(CreateOrderEvent event) {
        orderUseCases.create(event.getPayload());
    }
}
