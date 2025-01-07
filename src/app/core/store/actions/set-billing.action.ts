import { BillingModel } from "@core/models/billing.model";

export class SetBillingAction {
    static readonly type = '[Billing] Set Billing';
    constructor(public billing: BillingModel) { }
}