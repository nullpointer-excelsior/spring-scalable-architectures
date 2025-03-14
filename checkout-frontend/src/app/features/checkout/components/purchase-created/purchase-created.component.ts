import { Component, inject } from '@angular/core';
import { Store } from '@ngxs/store';
import { ConfirmationItemSummaryComponent } from '../confirmation-item-summary/confirmation-item-summary.component';
import { PurchaseState } from '@core/store/state/purchase.state';
import { CommonModule } from '@angular/common';

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
  purchase = this.store.selectSignal(PurchaseState.getPurchase)
}
