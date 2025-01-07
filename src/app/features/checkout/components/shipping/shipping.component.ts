import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@Component({
  selector: 'app-shipping',
  imports: [
    CheckoutButtonDirective,
    BorderIndicatorDirective,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './shipping.component.html'
})
export class ShippingComponent {

  shippingForm = new FormGroup({
    delivery: new FormControl('', Validators.required),
  })

  onContinue() {
    console.log('Shipping options:', this.shippingForm.value);
  }

}
