import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UIState } from '@core/store/state/ui.state';
import { Store } from '@ngxs/store';
import { StepIndicatorComponent } from "../step-indicator/step-indicator.component";

@Component({
  selector: 'app-checkout-steps',
  imports: [
    CommonModule,
    StepIndicatorComponent
],
  templateUrl: './checkout-steps.component.html'
})
export class CheckoutStepsComponent {
  private store = inject(Store);
  currentStep = this.store.selectSignal(UIState.getCheckoutCurrentStep);
}
