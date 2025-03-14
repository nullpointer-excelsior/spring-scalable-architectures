import { Purchase } from "../state/purchase.state";

export class CreatePurchaseAction {
    static readonly type = '[Purchase] Create Purchase';
    constructor(public purchase: Omit<Purchase, 'id'>) { }
}