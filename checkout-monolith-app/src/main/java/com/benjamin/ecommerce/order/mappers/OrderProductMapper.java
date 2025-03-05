package com.benjamin.ecommerce.order.mappers;

import com.benjamin.ecommerce.order.entities.OrderProductEntity;
import com.benjamin.ecommerce.order.models.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderProductEntity toEntity(OrderProduct product);
}
