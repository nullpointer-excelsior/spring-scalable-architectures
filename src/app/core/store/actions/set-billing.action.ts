import { BillingModel } from "@core/models/billing.model";

export class SetBillingAction {
    static readonly type = '[Checkout] Set Billing';
    constructor(public billing: BillingModel) { }
}