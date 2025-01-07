import { ShippingModel } from "@core/models/shipping.model";

export class SetShippingAction {
    static readonly type = '[Shipping] SetShipping';
    constructor(public shipping: ShippingModel) {}
}