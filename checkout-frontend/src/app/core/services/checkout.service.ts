import { inject, Injectable } from '@angular/core';
import { EcommerceApi } from '@core/services/ecommerce.api';
import { Store } from '@ngxs/store';
import { CartState } from '@core/store/state/cart.state';
import { CheckoutState } from '@core/store/state/checkout.state';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  
  private ecommerce = inject(EcommerceApi);
  private store = inject(Store)

  processPurchase() {
    const products = this.store.selectSnapshot(CartState.getProducts)
    const amount = this.store.selectSnapshot(CartState.getAmount)
    const shipping = this.store.selectSnapshot(CheckoutState.getShipping)
    const payment = this.store.selectSnapshot(CheckoutState.getPayment)
    const purchase = {
      order: {
        products: products.map(p => ({
          name: p.name,
          price: p.price,
          quantity: p.quantity,
          sku: p.sku
        })),
        amount
      },
      payment: {
        method: payment.method,
        details: {
          ...payment.details
        },
        amount
      },
      shipping: {
        ...shipping,
        option: shipping.delivery,
      }
    }
    return this.ecommerce.processPurchase(purchase)
  }

}
