package com.benjamin.ecommerce.shipping.listeners;

import com.benjamin.ecommerce.shared.integration.EventBus;
import com.benjamin.ecommerce.shared.integration.events.CreateShippingEvent;
import com.benjamin.ecommerce.shared.integration.events.ShippingCreatedEvent;
import com.benjamin.ecommerce.shipping.ShippingUseCases;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShippingListener {

    @Autowired
    private ShippingUseCases shippingUseCases;
    @Autowired
    private EventBus eventBus;

    @EventListener(CreateShippingEvent.class)
    public void onCreateShipping(CreateShippingEvent event) {
        var shippingCreated = shippingUseCases.create(event.getPayload());
        eventBus.dispatch(new ShippingCreatedEvent(shippingCreated));
    }
}
