import { BillingModel } from "@core/models/billing.model";
import { ShippingModel } from "@core/models/shipping.model";

export interface CheckoutModel {
    billing: BillingModel;
    shipping: ShippingModel;
}