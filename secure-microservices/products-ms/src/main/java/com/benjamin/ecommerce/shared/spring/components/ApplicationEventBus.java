package com.benjamin.ecommerce.shared.spring.components;

import com.benjamin.ecommerce.shared.integration.Event;
import com.benjamin.ecommerce.shared.integration.EventBus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationEventBus implements EventBus {

    private final ApplicationEventPublisher publisher;

    @Override
    public <T extends Event<?>> void dispatch(T event) {
        log.info("dispatching-event[{}]: {}", event.getClass().getSimpleName(), event.getPayload());
        this.publisher.publishEvent(event);
    }
}
