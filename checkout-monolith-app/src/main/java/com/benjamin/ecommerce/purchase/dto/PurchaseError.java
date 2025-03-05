package com.benjamin.ecommerce.purchase.dto;

public record PurchaseError(Long purchaseId, PurchaseErrorReason reason, String message) {
}
