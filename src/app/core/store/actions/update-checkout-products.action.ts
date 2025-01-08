import { ProductModel } from "@core/models/product.model";

export class UpdateCheckoutProductsAction {
    static readonly type = '[Checkout] Update Checkout Products';
    constructor(public products: ProductModel[]) {}
}