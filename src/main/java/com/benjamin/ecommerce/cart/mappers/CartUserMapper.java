package com.benjamin.ecommerce.cart.mappers;

import com.benjamin.ecommerce.cart.entities.CartUserEntity;
import com.benjamin.ecommerce.shared.core.cart.dto.CreateCartRequest;
import com.benjamin.ecommerce.shared.core.cart.models.CartUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartUserMapper {

    CartUser toModel(CartUserEntity cartUserEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(source = "user.email", target = "email")
    CartUserEntity toEntity(CreateCartRequest request);
}
