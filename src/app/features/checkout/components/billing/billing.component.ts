import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { InputTextComponent } from "@shared/components/input-text/input-text.component";
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-billing',
  imports: [
    BorderIndicatorDirective,
    InputTextComponent,
    CheckoutButtonDirective,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './billing.component.html'
})
export class BillingComponent {
  
  billingForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.billingForm = this.fb.group({
      fullname: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      cardName: ['', Validators.required],
      cardNumber: ['', Validators.required],
      expirationDate: ['', Validators.required],
      cvv: ['', Validators.required]
    });
  }

  onContinue() {
    console.log('Billing details:', this.billingForm.value);
  }

}
