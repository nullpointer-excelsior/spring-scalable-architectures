export class SetDeliveryAction {
    static readonly type = '[Checkout] SetDelivery';
    constructor(public delivery: string) {}
}