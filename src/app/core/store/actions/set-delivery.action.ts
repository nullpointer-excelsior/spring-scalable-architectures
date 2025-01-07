export class SetDeliveryAction {
    static readonly type = '[Shipping] SetDelivery';
    constructor(public delivery: string) {}
}