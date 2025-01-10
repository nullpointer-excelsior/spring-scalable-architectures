export class DeleteCartProductAction{
    static readonly type = '[Cart] Delete Cart Product';
    constructor(public sku: number) {}
}