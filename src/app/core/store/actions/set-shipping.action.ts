import { ShippingModel } from "@core/models/shipping.model";

export class SetShippingAction {
    static readonly type = '[Checkout] SetShipping';
    constructor(public shipping: ShippingModel) {}
}