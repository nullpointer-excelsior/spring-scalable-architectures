package com.benjamin.ecommerce.shipping.mappers;

import com.benjamin.ecommerce.purchase.dto.CreateShipping;
import com.benjamin.ecommerce.shipping.entities.ShippingEntity;
import com.benjamin.ecommerce.shipping.models.Shipping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {DeliveryMapper.class})
public interface ShippingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "shippedAt", ignore = true)
    @Mapping(target = "deliveredAt", ignore = true)
    ShippingEntity toUnPersistedEntity(CreateShipping request);

    Shipping toModel(ShippingEntity entity);
}
