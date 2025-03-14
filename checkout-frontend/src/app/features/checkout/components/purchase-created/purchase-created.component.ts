import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { CheckoutState } from '@core/store/state/checkout.state';
import { ConfirmationItemSummaryComponent } from '@features/checkout/components/confirmation-item-summary/confirmation-item-summary.component';
import { Store } from '@ngxs/store';

@Component({
  selector: 'app-purchase-created',
  imports: [
    ConfirmationItemSummaryComponent,
    CommonModule
  ],
  templateUrl: './purchase-created.component.html',
  styles: ``
})
export class PurchaseCreatedComponent {
  private store = inject(Store)
  purchase = this.store.selectSignal(CheckoutState.getPurchase)
}
