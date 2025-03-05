package com.benjamin.ecommerce.shared.components;

import com.benjamin.ecommerce.shared.integration.events.PurchaseCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestEventListener {
    public final List<PurchaseCanceledEvent> purchaseCanceledEvents = new ArrayList<>();

    @EventListener(PurchaseCanceledEvent.class)
    void onEvent(PurchaseCanceledEvent event) {
        purchaseCanceledEvents.add(event);
    }

    public void reset() {
        purchaseCanceledEvents.clear();
    }
}
