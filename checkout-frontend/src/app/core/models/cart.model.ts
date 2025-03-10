import { ProductModel } from "@core/models/product.model";

export interface CartModel {
    id: number;
    products: ProductModel[];
    amount: number;
    productsQuantity: number;
}

