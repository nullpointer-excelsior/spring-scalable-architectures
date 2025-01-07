import { PaymentMethodModel } from "@core/models/billing.model";

export class SetPaymentMethodAction {
    static readonly type = '[Billing] Set Payment Method';
    constructor(public paymentMethod: PaymentMethodModel) { }
}