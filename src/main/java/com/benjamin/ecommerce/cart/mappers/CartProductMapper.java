package com.benjamin.ecommerce.cart.mappers;

import com.benjamin.ecommerce.cart.entities.CartProductEntity;
import com.benjamin.ecommerce.shared.core.cart.models.CartProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartProductMapper {

    CartProduct toModel(CartProductEntity cartProductEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    List<CartProductEntity> toUnPersistedEntities(List<CartProduct> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    CartProductEntity toUnPersistedEntity(CartProduct product);
}
