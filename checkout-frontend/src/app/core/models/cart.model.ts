import { ProductModel } from "@core/models/product.model";

export interface CartModel {
    products: ProductModel[];
    amount: number;
    productsQuantity: number;
}

