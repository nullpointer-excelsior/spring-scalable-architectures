import { Component, inject } from '@angular/core';
import { CartState } from '@core/store/state/cart.state';
import { CheckoutState } from '@core/store/state/checkout.state';
import { CartListComponent } from "@features/checkout/components/cart-list/cart-list.component";
import { ConfirmationItemSummaryComponent } from "@features/checkout/components/confirmation-item-summary/confirmation-item-summary.component";
import { Store } from '@ngxs/store';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';
import { SetCurrentStep } from '@core/store/actions/ui.actions';

@Component({
  selector: 'app-confirmation',
  imports: [
    CartListComponent, 
    BorderIndicatorDirective, 
    CheckoutButtonDirective, 
    ConfirmationItemSummaryComponent, 
  ],
  templateUrl: './confirmation.component.html'
})
export class ConfirmationComponent {
  
  private store: Store = inject(Store);
  products = this.store.selectSignal(CartState.getProducts);
  summary = this.store.selectSignal(CheckoutState.getCheckoutSummary);

  constructor() {
    this.store.dispatch(new SetCurrentStep(3))
  }

}
