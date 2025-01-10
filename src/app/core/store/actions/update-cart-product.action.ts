import { ProductModel } from "@core/models/product.model";

export class UpdateCartProductAction {
    static readonly type = '[Cart] Update Cart Product';
    constructor(public product: ProductModel) {}
}