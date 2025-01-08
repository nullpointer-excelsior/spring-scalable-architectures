import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UIState } from '@core/store/state/ui.state';
import { Store } from '@ngxs/store';

@Component({
  selector: 'app-checkout-steps',
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './checkout-steps.component.html'
})
export class CheckoutStepsComponent {
  private store = inject(Store);
  currentStep = this.store.selectSignal(UIState.getCheckoutCurrentStep);
}
