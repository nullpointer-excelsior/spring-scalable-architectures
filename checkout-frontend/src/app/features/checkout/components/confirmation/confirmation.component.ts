import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { CreatePurchaseAction } from '@core/store/actions/checkout.actions';
import { CartState } from '@core/store/state/cart.state';
import { CheckoutState } from '@core/store/state/checkout.state';
import { CartListComponent } from "@features/checkout/components/cart-list/cart-list.component";
import { ConfirmationItemSummaryComponent } from "@features/checkout/components/confirmation-item-summary/confirmation-item-summary.component";
import { UntilDestroy } from '@ngneat/until-destroy';
import { Store } from '@ngxs/store';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@UntilDestroy()
@Component({
  selector: 'app-confirmation',
  imports: [
    CartListComponent,
    CheckoutButtonDirective,
    ConfirmationItemSummaryComponent,
    CommonModule
  ],
  templateUrl: './confirmation.component.html'
})
export class ConfirmationComponent {

  private store: Store = inject(Store);
  products = this.store.selectSignal(CartState.getProducts);
  summary = this.store.selectSignal(CheckoutState.getCheckoutSummary);

  onConfirm() {
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
    this.store.dispatch(new CreatePurchaseAction(purchase))
  }

}
