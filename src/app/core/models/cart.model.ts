import { ProductModel } from "@core/models/product.model";

export interface CartModel {
    products: ProductModel[];
    total: number;
}

