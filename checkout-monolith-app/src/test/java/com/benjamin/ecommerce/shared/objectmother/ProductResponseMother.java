package com.benjamin.ecommerce.shared.objectmother;

import com.benjamin.ecommerce.products.models.Product;

public class ProductResponseMother {

    public static ProductModelBuilder builder() {
        return new ProductModelBuilder();
    }

    public static class ProductModelBuilder {
        private String sku = "1111";
        private String name = "guitar";
        private Double price = 1000.0;
        private Integer quantity = 1;

        public ProductModelBuilder withSku(String sku) {
            this.sku = sku;
            return this;
        }

        public ProductModelBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductModelBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public ProductModelBuilder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product build() {
      return new Product(sku, name, price, quantity);
        }
    }
}

