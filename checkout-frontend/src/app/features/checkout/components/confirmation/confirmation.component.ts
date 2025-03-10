import { Component, inject, OnInit } from '@angular/core';
import { SetCurrentStep } from '@core/store/actions/ui.actions';
import { CartState } from '@core/store/state/cart.state';
import { CheckoutState } from '@core/store/state/checkout.state';
import { CartListComponent } from "@features/checkout/components/cart-list/cart-list.component";
import { ConfirmationItemSummaryComponent } from "@features/checkout/components/confirmation-item-summary/confirmation-item-summary.component";
import { Store } from '@ngxs/store';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';
import { CheckoutService } from '@core/services/checkout.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-confirmation',
  imports: [
    CartListComponent, 
    CheckoutButtonDirective, 
    ConfirmationItemSummaryComponent, 
  ],
  templateUrl: './confirmation.component.html'
})
export class ConfirmationComponent implements OnInit {
  
  private store: Store = inject(Store);
  private checkout = inject(CheckoutService)
  private toastr =  inject(ToastrService)
  products = this.store.selectSignal(CartState.getProducts);
  summary = this.store.selectSignal(CheckoutState.getCheckoutSummary);

  ngOnInit(): void {
    this.store.dispatch(new SetCurrentStep(3))
  }

  onConfirm() {
    this.checkout.processPurchase()
    .subscribe({
      next: () => this.toastr.success("purchase process started", 'Order started'),
      error: (err) => this.toastr.error(err.message, "Error purchase")
    })
  }

}
