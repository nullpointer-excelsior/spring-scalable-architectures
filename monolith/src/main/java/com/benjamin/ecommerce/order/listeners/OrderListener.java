package com.benjamin.ecommerce.order.listeners;

import com.benjamin.ecommerce.order.OrderUseCases;
import com.benjamin.ecommerce.shared.integration.EventBus;
import com.benjamin.ecommerce.shared.integration.events.CreateOrderEvent;
import com.benjamin.ecommerce.shared.integration.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

    @Autowired
    private OrderUseCases orderUseCases;
    @Autowired
    private EventBus eventBus;

    @EventListener(CreateOrderEvent.class)
    public void onCreateOrderEvent(CreateOrderEvent event) {
        var orderCreated = orderUseCases.create(event.getPayload());
        eventBus.dispatch(new OrderCreatedEvent(orderCreated));
    }
}
