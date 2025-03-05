import { CartModel } from "@core/models/cart.model";
import { ProductModel } from "@core/models/product.model";

export class UpdateCartProductsAction {
    static readonly type = '[Cart] Update Checkout Products';
    constructor(public products: ProductModel[]) {}
}

export class UpdateCartProductAction {
    static readonly type = '[Cart] Update Cart Product';
    constructor(public product: ProductModel) {}
}

export class SetCartAction {
    static readonly type = '[Cart] Set Cart';
    constructor(public cart: CartModel) { }
}

export class DeleteCartProductAction{
    static readonly type = '[Cart] Delete Cart Product';
    constructor(public sku: number) {}
}


