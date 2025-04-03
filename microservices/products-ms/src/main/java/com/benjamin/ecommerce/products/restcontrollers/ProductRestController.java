package com.benjamin.ecommerce.products.restcontrollers;

import com.benjamin.ecommerce.products.ProductUseCases;
import com.benjamin.ecommerce.products.dto.UpdateProductQuantity;
import com.benjamin.ecommerce.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PATCH})
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private ProductUseCases productUseCases;

    @GetMapping
    public List<Product> getAll() {
        return this.productUseCases.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("quantity")
    public void updateProductQuantityBatch(@RequestBody List<UpdateProductQuantity> batch) {
        productUseCases.updateProductQuantityBatch(batch);
    }

}
