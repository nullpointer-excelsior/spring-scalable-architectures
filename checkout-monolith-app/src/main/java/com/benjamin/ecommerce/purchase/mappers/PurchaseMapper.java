package com.benjamin.ecommerce.purchase.mappers;

import com.benjamin.ecommerce.purchase.entities.PurchaseEntity;
import com.benjamin.ecommerce.purchase.models.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    Purchase toModel(PurchaseEntity entity);
}
