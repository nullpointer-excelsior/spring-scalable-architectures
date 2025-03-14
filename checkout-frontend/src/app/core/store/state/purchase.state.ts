import { inject, Injectable } from "@angular/core";
import { Action, Selector, State, StateContext, Store } from "@ngxs/store";
import { CreatePurchaseAction } from "../actions/purchase.actions";
import { EcommerceApi } from "@core/services/ecommerce.api";
import { tap } from "rxjs";
import { PaymentMethod } from "../../models/billing.model";
import { Delivery } from "../../models/shipping.model";

export type Purchase = {
  id: number;
  order: {
    products: {
      sku: number;
      name: string;
      price: number;
      quantity: number;
    }[],
    amount: number;
  },
  payment: {
    method: string;
    amount: number;
    details: Record<string, number | string>
  },
  shipping: {
    fullname: string;
    address: string;
    city: string;
    option: string;
  }
}

@State<Purchase>({
  name: 'purchase',
  defaults: {
    id: 1,
    order: {
      amount: 1000,
      products: [{
        name: 'Bc rich electric guitar',
        price: 100,
        quantity: 1,
        sku: 2
      },
      {
        name: 'Marshall APM 100W',
        price: 100,
        quantity: 1,
        sku: 2
      }
    ]
    },
    payment: {
      amount: 1000,
      details: {
        cvv: 123
      },
      method: PaymentMethod.CreditCard
    },
    shipping: {
      address: 'st evergreen',
      city: 'springfield',
      fullname: 'homer simpson',
      option: Delivery.FREE
    }
  }
})
@Injectable({
  providedIn: 'root'
})
export class PurchaseState {

  private store = inject(Store)
  private ecommerce = inject(EcommerceApi);

  @Action(CreatePurchaseAction)
  createPurchase(ctx: StateContext<Purchase>, action: CreatePurchaseAction) {
    return this.ecommerce.processPurchase(action.purchase).pipe(
      tap(res => ctx.setState({
        id: res.purchaseId,
        ...action.purchase
      }))
    )
  }

  @Selector()
  static getPurchase(state: Purchase) {
    return state
  }

}