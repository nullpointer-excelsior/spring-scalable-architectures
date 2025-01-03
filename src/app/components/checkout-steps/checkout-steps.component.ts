import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-checkout-steps',
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './checkout-steps.component.html'
})
export class CheckoutStepsComponent {
  currentStep = 2;
}
