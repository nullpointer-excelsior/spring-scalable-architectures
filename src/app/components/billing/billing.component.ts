import { Component } from '@angular/core';
import { BorderIndicatorDirective } from '../../directives/border-indicator.directive';
import { InputTextComponent } from "../shared/input-text/input-text.component";
import { CheckoutButtonDirective } from '../../directives/checkout-button.directive';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-billing',
  imports: [
    BorderIndicatorDirective,
    InputTextComponent,
    CheckoutButtonDirective,
    RouterLink
  ],
  templateUrl: './billing.component.html'
})
export class BillingComponent {

}
