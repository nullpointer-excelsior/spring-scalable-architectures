import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CheckoutStepsComponent } from "@features/checkout/components/checkout-steps/checkout-steps.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CheckoutStepsComponent],
  templateUrl: './app.component.html'
})
export class AppComponent {

}
