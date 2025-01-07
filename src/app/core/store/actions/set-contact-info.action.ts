import { ContactInfoModel } from "@core/models/billing.model";

export class SetContactInfoAction {
    static readonly type = '[Billing] Set Contact Info';
    constructor(public contactInfo: ContactInfoModel) { }
}