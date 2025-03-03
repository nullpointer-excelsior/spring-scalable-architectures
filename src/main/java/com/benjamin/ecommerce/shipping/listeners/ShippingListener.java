package com.benjamin.ecommerce.shipping.listeners;

import com.benjamin.ecommerce.shared.integration.EventBus;
import com.benjamin.ecommerce.shared.integration.events.CreateShippingEvent;
import com.benjamin.ecommerce.shared.integration.events.ShippingCreatedEvent;
import com.benjamin.ecommerce.shipping.ShippingUseCases;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
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
