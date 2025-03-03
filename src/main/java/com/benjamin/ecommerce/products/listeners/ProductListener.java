package com.benjamin.ecommerce.products.listeners;

import com.benjamin.ecommerce.products.ProductUseCases;
import com.benjamin.ecommerce.shared.integration.events.UpdateProductStockEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ProductListener {

    @Autowired
    private ProductUseCases productUseCases;

    @EventListener(UpdateProductStockEvent.class)
    public void onUpdateProductStockEvent(UpdateProductStockEvent event) {
        productUseCases.updateProductQuantityBatch(event.getPayload());
    }
}
