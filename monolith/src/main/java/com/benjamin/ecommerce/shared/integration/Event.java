package com.benjamin.ecommerce.shared.integration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
public abstract class Event <T>{
    @NotEmpty
    private final String id;
    @NotNull
    @PastOrPresent
    private final LocalDateTime createdAt;
    @Valid
    @NotNull
    private final T payload;

    public Event(T payload) {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.payload = payload;
    }
}
