import { Component, inject, OnInit, Signal } from '@angular/core';
import { ItemCartModel } from '@core/models/cart.model';
import { CartState } from '@core/store/state/cart.state';
import { CartListComponent } from "@features/checkout/components/cart-list/cart-list.component";
import { Store } from '@ngxs/store';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';
import { ConfirmationItemSummaryComponent } from "@features/checkout/components/confirmation-item-summary/confirmation-item-summary.component";

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
export class ConfirmationComponent implements OnInit {
  
  store: Store = inject(Store);
  cartItems: Signal<ItemCartModel[]>= this.store.selectSignal(CartState.getItems);

  ngOnInit(): void {
    
  }

}
