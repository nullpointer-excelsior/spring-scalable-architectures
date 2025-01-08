import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Store } from '@ngxs/store';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';
import { SetCartAction } from '@core/store/actions/set-cart.action';
import { SetShippingAction } from '@core/store/actions/set-shipping.action';
import { SetUserAction } from '@core/store/actions/set-user.action';
import { getCartItems } from '@core/utils/get-cart-items';
import { getRandomElements } from '@core/utils/get-random-elements';
import { getUsers } from '@core/utils/get-users';

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
export class ShippingComponent implements OnInit {

  shippingForm = new FormGroup({
    delivery: new FormControl('', Validators.required),
  })
  private store = inject(Store);

  ngOnInit(): void {
    const user = getRandomElements(getUsers(), 1)[0];
    const items = getRandomElements(getCartItems(), 3);
    this.store.dispatch(new SetUserAction(user));
    this.store.dispatch(new SetCartAction({ items }))
  }

  onContinue() {
    this.store.dispatch(new SetShippingAction({
      delivery: this.shippingForm.value.delivery
    }))
  }

}
