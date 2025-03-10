import { ProductModel } from "@core/models/product.model";


export class UpdateCartProductAction {
    static readonly type = '[Cart] Update Cart Product';
    constructor(public product: ProductModel) { }
}

export class CreateCartAction {
    static readonly type = '[Cart] Create Cart';
    constructor(public cart: { user: { id: number; email: string }, products: ProductModel[] }) { }
}

export class DeleteCartProductAction {
    static readonly type = '[Cart] Delete Cart Product';
    constructor(public product: ProductModel) { }
}


