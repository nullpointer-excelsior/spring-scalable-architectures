import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Delivery } from '@core/models/shipping.model';
import { SetCurrentStep } from '@core/store/actions/set-current-step.action';
import { SetShippingAction } from '@core/store/actions/set-shipping.action';
import { SetUserAction } from '@core/store/actions/set-user.action';
import { UpdateCheckoutProductsAction } from '@core/store/actions/update-checkout-products.action';
import { getCartItems as getCartProducts } from '@core/utils/get-cart-items';
import { getRandomElements } from '@core/utils/get-random-elements';
import { getUsers } from '@core/utils/get-users';
import { Store } from '@ngxs/store';
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
export class ShippingComponent implements OnInit {

  deliveryOptions = Delivery
  shippingForm = new FormGroup({
    delivery: new FormControl(this.deliveryOptions.Standard, Validators.required),
  })
  private store = inject(Store);

  ngOnInit(): void {
    const user = getRandomElements(getUsers(), 1)[0];
    const products = getRandomElements(getCartProducts(), 3);
    this.store.dispatch(new SetUserAction(user));
    this.store.dispatch(new UpdateCheckoutProductsAction(products));
    this.store.dispatch(new SetCurrentStep(1))
  }

  onContinue() {
    this.store.dispatch(new SetShippingAction({
      delivery: this.shippingForm.value.delivery
    }))
  }

}
