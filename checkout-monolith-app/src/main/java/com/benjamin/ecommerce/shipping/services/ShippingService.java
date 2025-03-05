package com.benjamin.ecommerce.shipping.services;

import com.benjamin.ecommerce.purchase.dto.CreateShipping;
import com.benjamin.ecommerce.shared.integration.EventBus;
import com.benjamin.ecommerce.shared.integration.events.ShippingUpdatedEvent;
import com.benjamin.ecommerce.shipping.ShippingUseCases;
import com.benjamin.ecommerce.shipping.mappers.DeliveryMapper;
import com.benjamin.ecommerce.shipping.mappers.ShippingMapper;
import com.benjamin.ecommerce.shipping.models.Shipping;
import com.benjamin.ecommerce.shipping.models.ShippingStatus;
import com.benjamin.ecommerce.shipping.repositories.ShippingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class ShippingService implements ShippingUseCases {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private EventBus eventBus;

    @Override
    public Shipping create(CreateShipping request) {
        var deliveryEntity = deliveryMapper.toUnPersistedEntity(request);
        var shippingEntity = shippingMapper.toUnPersistedEntity(request);
        shippingEntity.setDelivery(deliveryEntity);
        deliveryEntity.setShipping(shippingEntity);
        shippingEntity.setShippedAt(LocalDateTime.now());
        shippingEntity.setStatus(ShippingStatus.SHIPPED);
        shippingRepository.save(shippingEntity);
        return shippingMapper.toModel(shippingEntity);
    }

    @Override
    public Shipping update(Shipping shipping) {
        var entity = shippingMapper.toEntity(shipping);
        if (shipping.status().equals(ShippingStatus.DELIVERED)) {
            entity.setDeliveredAt(LocalDateTime.now());
        }
        shippingRepository.save(entity);
        var model = shippingMapper.toModel(entity);
        eventBus.dispatch(new ShippingUpdatedEvent(model));
        return model;
    }
}
