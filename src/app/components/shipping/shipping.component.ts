import { Component } from '@angular/core';
import { CheckoutButtonDirective } from '../../directives/checkout-button.directive';
import { BorderIndicatorDirective } from '../../directives/border-indicator.directive';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-shipping',
  imports: [
    CheckoutButtonDirective,
    BorderIndicatorDirective,
    RouterLink
  ],
  templateUrl: './shipping.component.html'
})
export class ShippingComponent {

}
