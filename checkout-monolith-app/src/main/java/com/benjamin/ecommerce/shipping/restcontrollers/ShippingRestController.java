package com.benjamin.ecommerce.shipping.restcontrollers;

import com.benjamin.ecommerce.shipping.ShippingUseCases;
import com.benjamin.ecommerce.shipping.models.Shipping;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
public class ShippingRestController {

    @Autowired
    private ShippingUseCases shippingUseCases;

    @PutMapping
    public Shipping update(@Valid @RequestBody Shipping shipping) {
        return shippingUseCases.update(shipping);
    }
}
