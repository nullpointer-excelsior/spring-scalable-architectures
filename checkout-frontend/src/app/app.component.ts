import { Component, inject, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CheckoutStepsComponent } from "@features/checkout/components/checkout-steps/checkout-steps.component";
import { Store } from '@ngxs/store';
import { CreateRandomCheckoutAction } from '@core/store/actions/checkout.actions';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CheckoutStepsComponent],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  private store = inject(Store)

  ngOnInit(): void {
    this.store.dispatch(new CreateRandomCheckoutAction())
  }

}
