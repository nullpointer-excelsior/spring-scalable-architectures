import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Store } from '@ngxs/store';
import { StepIndicatorComponent } from "@features/checkout/components/step-indicator/step-indicator.component";
import { CheckoutStepsState } from '@core/store/state/checkout-steps.state';

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
  currentStep = this.store.selectSignal(CheckoutStepsState.getCurrentStep);
  loadingStep = this.store.selectSignal(CheckoutStepsState.getLoadingStep);
  loadingStepMessage = this.store.selectSignal(CheckoutStepsState.getLoadingStepMessage);
}
