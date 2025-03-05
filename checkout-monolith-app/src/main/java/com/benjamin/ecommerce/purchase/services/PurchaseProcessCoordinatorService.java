package com.benjamin.ecommerce.purchase.services;

import com.benjamin.ecommerce.products.dto.UpdateProductQuantity;
import com.benjamin.ecommerce.purchase.dto.*;
import com.benjamin.ecommerce.purchase.entities.PurchaseEntity;
import com.benjamin.ecommerce.purchase.mappers.PurchaseMapper;
import com.benjamin.ecommerce.shared.integration.events.*;
import com.benjamin.ecommerce.purchase.repositories.PurchaseRepository;
import com.benjamin.ecommerce.purchase.repositories.PurchaseRequestRepository;
import com.benjamin.ecommerce.purchase.entities.PurchaseRequestEntity;
import com.benjamin.ecommerce.order.models.Order;
import com.benjamin.ecommerce.order.models.OrderProduct;
import com.benjamin.ecommerce.payment.models.Payment;
import com.benjamin.ecommerce.payment.models.PaymentStatus;
import com.benjamin.ecommerce.purchase.PurchaseProcessCoordinator;
import com.benjamin.ecommerce.purchase.models.PurchaseStatus;
import com.benjamin.ecommerce.shared.integration.EventBus;
import com.benjamin.ecommerce.shared.utils.MapBuilder;
import com.benjamin.ecommerce.shipping.models.DeliveryOption;
import com.benjamin.ecommerce.shipping.models.Shipping;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Component
public class PurchaseProcessCoordinatorService implements PurchaseProcessCoordinator {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public PurchaseCreatedResponse process(CreatePurchaseRequest request) {
        log.info("purchase-process-started: {}", request);
        var o = new MapBuilder<>()
                .record("products", request.order().products())
                .record("amount", request.order().amount())
                .build();
        var p = new MapBuilder<>()
                .record("method", request.payment().method())
                .record("details", request.payment().details())
                .record("amount", request.payment().amount())
                .build();
        var s = new MapBuilder<>()
                .record("fullname", request.shipping().fullname())
                .record("address", request.shipping().address())
                .record("city", request.shipping().city())
                .record("option", request.shipping().option())
                .build();
        var purchaseRequest = PurchaseRequestEntity.builder()
                .method(request.payment().method())
                .amount(request.payment().amount())
                .orderRequest(o)
                .paymentRequest(p)
                .shippingRequest(s)
                .build();

        var purchase = PurchaseEntity.builder()
                .status(PurchaseStatus.CREATED)
                .purchaseRequest(purchaseRequest)
                .build();

        purchaseRepository.save(purchase);
        eventBus.dispatch(new CreatePaymentEvent(CreatePayment.builder()
                .purchaseId(purchase.getId())
                .amount(request.payment().amount())
                .details(request.payment().details())
                .method(request.payment().method())
                .build()));

        return new PurchaseCreatedResponse(purchase.getPurchaseRequest().getId(), purchase.getId());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(Payment payment) {

        if (payment.status().equals(PaymentStatus.ACCEPTED)) {

            var purchase = purchaseRepository
                    .findById(payment.purchaseId())
                    .orElseThrow(() -> new NoSuchElementException("Purchase not found"));
            purchase.setStatus(PurchaseStatus.PAYED);
            var orderRequest = purchase.getPurchaseRequest().getOrderRequest();
            var shippingRequest = purchase.getPurchaseRequest().getShippingRequest();
            var orderProducts = (List<OrderProduct>) orderRequest.get("products");
            purchaseRepository.save(purchase);

            eventBus.dispatch(new CreateOrderEvent(CreateOrder.builder()
                    .purchaseId(purchase.getId())
                    .amount((Double) orderRequest.get("amount"))
                    .products(orderProducts)
                    .build()));
            eventBus.dispatch(new CreateShippingEvent(CreateShipping.builder()
                    .purchaseId(purchase.getId())
                    .address((String) shippingRequest.get("address"))
                    .city((String) shippingRequest.get("city"))
                    .fullname((String) shippingRequest.get("fullname"))
                    .option((DeliveryOption) shippingRequest.get("option"))
                    .build()));
            eventBus.dispatch(new UpdateProductStockEvent(orderProducts.stream()
                    .map(op -> new UpdateProductQuantity(op.sku(), op.quantity()))
                    .toList()));
        }
    }

    @Override
    public void process(Order order) {
        updatePurchaseStatus(order.purchaseId(), PurchaseStatus.ORDERED);
    }

    @Override
    public void process(Shipping shipping) {
        updatePurchaseStatus(shipping.purchaseId(), PurchaseStatus.SHIPPED);
    }

    @Override
    public void process(CompletePurchase completePurchase) {
        updatePurchaseStatus(completePurchase.purchaseId(), PurchaseStatus.COMPLETED);
    }

    @Override
    public void process(PurchaseError purchaseError) {
        purchaseRepository.findById(purchaseError.purchaseId()).ifPresent(purchase -> {
            purchase.setStatus(PurchaseStatus.CANCELED);
            purchaseRepository.save(purchase);
            eventBus.dispatch(new PurchaseCanceledEvent(purchaseMapper.toModel(purchase)));
            log.warn("purchase-process-canceled: {}", purchase);
        });
    }

    private void updatePurchaseStatus(Long purchaseId, PurchaseStatus status) {
        purchaseRepository.findById(purchaseId).ifPresent(purchase -> {
            purchase.setStatus(status);
            purchaseRepository.save(purchase);
        });
    }



}
