import { PaymentModel } from "@core/models/billing.model";

export class SetPaymentMethodAction {
    static readonly type = '[Checkout] Set Payment Method';
    constructor(public paymentMethod: PaymentModel) { }
}