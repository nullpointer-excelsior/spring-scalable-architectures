import { Component, effect, inject, OnInit, Signal } from '@angular/core';
import { Router } from '@angular/router';
import { ItemCartModel } from '@core/models/cart.model';
import { CartState } from '@core/store/state/cart.state';
import { CartListComponent } from "@features/checkout/components/cart-list/cart-list.component";
import { ConfirmationItemSummaryComponent } from "@features/checkout/components/confirmation-item-summary/confirmation-item-summary.component";
import { Store } from '@ngxs/store';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

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
  
  private store: Store = inject(Store);
  private router = inject(Router);
  cartItems: Signal<ItemCartModel[]> = this.store.selectSignal(CartState.getItems);
  total: Signal<number> = this.store.selectSignal(CartState.getTotal);

  constructor() {
    effect(() => {
      if (this.total() === 0) this.router.navigate(['/shipping']);
    })
  }

  ngOnInit(): void {
   
  }

}
