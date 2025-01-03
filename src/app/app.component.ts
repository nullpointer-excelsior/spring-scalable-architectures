import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CheckoutStepsComponent } from "./components/checkout-steps/checkout-steps.component";
import { BorderIndicatorDirective } from './directives/border-indicator.directive';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CheckoutStepsComponent],
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'checkout-frontend';
}
