package com.benjamin.ecommerce.shared.integration;

public interface EventBus {
    <T extends Event<?>> void dispatch(T event);
}
