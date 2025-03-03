package com.benjamin.ecommerce.shipping.mappers;

import com.benjamin.ecommerce.purchase.dto.CreateShipping;
import com.benjamin.ecommerce.shipping.entities.DeliveryEntity;
import com.benjamin.ecommerce.shipping.models.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeliveryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shipping", ignore = true)
    DeliveryEntity toUnPersistedEntity(CreateShipping request);
    @Mapping(target = "shipping", ignore = true)
    DeliveryEntity toEntity(Delivery delivery);
}
