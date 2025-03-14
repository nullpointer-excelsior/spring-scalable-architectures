import { ContactInfoModel } from "@core/models/billing.model";
import { PaymentModel } from "@core/models/billing.model";
import { ShippingModel } from "@core/models/shipping.model";
import { PurchaseModel } from "@core/models/purchase.model";

export class SetShippingAction {
    static readonly type = '[Checkout] SetShipping';
    constructor(public shipping: ShippingModel) {}
}

export class SetPaymentMethodAction {
    static readonly type = '[Checkout] Set Payment Method';
    constructor(public paymentMethod: PaymentModel) { }
}

export class SetBillingAction {
    static readonly type = '[Checkout] Set Billing';
    constructor(public billing: {
        contactInfo: ContactInfoModel;
        payment: Omit<PaymentModel, 'methodStatus'>;
      }) { }
}

export class CreateRandomCheckoutAction {
    static readonly type = '[Checkout] Create Random Checkout';
}

export class SetContactInfoAction {
    static readonly type = '[Checkout] Set Contact Info';
    constructor(public contactInfo: ContactInfoModel) { }
}

export class SetDeliveryAction {
    static readonly type = '[Checkout] SetDelivery';
    constructor(public delivery: string) {}
}

export class CreatePurchaseAction {
    static readonly type = '[Checkout] Create Purchase';
    constructor(public purchase: Omit<PurchaseModel, 'id'>) { }
}