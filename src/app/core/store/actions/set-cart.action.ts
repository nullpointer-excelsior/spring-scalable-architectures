import { CartModel } from "@core/models/cart.model";

export class SetCartAction {
    static readonly type = '[Cart] Set Cart';
    constructor(public cart: CartModel) { }
}