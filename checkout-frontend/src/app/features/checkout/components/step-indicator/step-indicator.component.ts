import { CommonModule } from '@angular/common';
import { Component, inject, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Store } from '@ngxs/store';
import { SetCurrentStepAction } from '@core/store/actions/checkout-steps.actions';

@Component({
  selector: 'app-step-indicator',
  imports: [
    CommonModule,
    RouterLink,
  ],
  templateUrl: './step-indicator.component.html',
  styles: ``
})
export class StepIndicatorComponent {

  currentStep = input(0)
  step = input(0)
  name = input('')
  link = input('')
  color = input('blue-500')
  private store = inject(Store)

  onClick() {
    this.store.dispatch(new SetCurrentStepAction(this.step()))
  }
}
