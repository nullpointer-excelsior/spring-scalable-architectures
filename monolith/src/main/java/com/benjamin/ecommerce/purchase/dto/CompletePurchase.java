package com.benjamin.ecommerce.purchase.dto;

import jakarta.validation.constraints.NotNull;

public record CompletePurchase(@NotNull Long purchaseId) {}
