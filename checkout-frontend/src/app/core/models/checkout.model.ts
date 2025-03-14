import { BillingModel } from "@core/models/billing.model";
import { ShippingModel } from "@core/models/shipping.model";
import { PurchaseModel } from "@core/models/purchase.model";

export interface CheckoutModel {
    billing: BillingModel;
    shipping: ShippingModel;
    purchase: PurchaseModel
}